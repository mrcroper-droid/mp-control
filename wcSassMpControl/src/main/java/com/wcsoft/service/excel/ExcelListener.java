package com.wcsoft.service.excel;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
/**
 * @author : harara
 * @version : 2.0
 * @date : 2020/6/10 9:25
 */
public class ExcelListener extends AnalysisEventListener {


    private List<Object> dataList = new ArrayList<Object>();
    /**
     * 通过AbalysisContext可以获取当前sheet,当前行等数据
     * @param object
     * @param context
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        dataList.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //dosomething
    }


    public List<Object> getDataList(){
        return dataList;
    }
    


}