package com.wcsoft.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * 多边形算法
 * @author Administrator
 *
 */
public class Polygon {  
    private final Double[] polyX, polyY;  
    private final int polyPoints;  
  
    public Polygon(Double[] polyX, Double[] polyY, int polyPoints) {  
        this.polyX = polyX;  
        this.polyY = polyY;  
        this.polyPoints = polyPoints;  
    }  
  
    // 判断一个坐标是否在这个多边形内  
    public boolean contains(Double x, Double y) {  
        boolean oddTransitions = false;  
        for (int i = 0, j = polyPoints - 1; i < polyPoints; j = i++) {  
            if ((polyY[i] <= y && polyY[j] >= y) || (polyY[j] <= y && polyY[i] >= y)) {  
                if (polyX[i] + (y - polyY[i]) / (polyY[j] - polyY[i]) * (polyX[j] - polyX[i]) <= x) {  
                    oddTransitions = !oddTransitions;  
                }  
            }  
        }  
        return oddTransitions;  
    }
    /**
     * 检查是否在范围内
     * @param pointData 数据库里存的范围数据[{"lat":24.88295217241512,"lng":102.83117294311523}]
     * @param lng 待校验的lng
     * @param lat 待校验的lat
     * @return
     */
    public static boolean checkPoint(String pointData, String lng, String lat) {
    	JSONArray pointArr = JSONArray.parse(pointData);
		List<Double> polyXArr = new ArrayList<>(); 
		List<Double> polyYArr = new ArrayList<>(); 
		for(Object job: pointArr) {
			polyXArr.add(Double.valueOf(((JSONObject)job).getString("lng")));
			polyYArr.add(Double.valueOf(((JSONObject)job).getString("lat")));
		}
		Double[] polyX = new Double[polyXArr.size()];
		polyX = polyXArr.toArray(polyX);
		Double[] polyY = new Double[polyYArr.size()];
		polyY = polyYArr.toArray(polyY);
		
		Polygon polygon = new Polygon(polyX, polyY, polyYArr.size());
		return polygon.contains(Double.valueOf(lng), Double.valueOf(lat));
    }
}