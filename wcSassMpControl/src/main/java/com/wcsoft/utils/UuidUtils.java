package com.wcsoft.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class UuidUtils {
 
    private static AtomicLong id;
 
 
    public static String getIdString(String perfix) {
    	return perfix.concat(String.valueOf(getId()).substring(8,14));
    }
    
    /**
     * 生成Long 类型唯一ID
     */
    public synchronized static Long getId() {
        //如果需要更长 或者更大冗余空间, 只需要 time * 10^n   即可  
        //当前可保证1毫秒 生成 10000条不重复 
        Long time = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))*1000;
        if (id == null) {
            id = new AtomicLong(time);
            return id.get();
        }
        if (time <= id.get()) {
            id.addAndGet(1);
        } else {
            id = new AtomicLong(time);
        }
        return id.get();
    }
 
    public static void main(String[] args) throws InterruptedException {
        Set<Long> set = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
            {
                Long id = getId();
                set.add(id);
                System.out.println(id);
            }
            ).start();
        }
        Thread.sleep(5000);
        int size = set.size();
        System.out.println(size);
    }
}
