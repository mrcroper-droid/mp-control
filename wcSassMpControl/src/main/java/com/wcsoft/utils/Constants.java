package com.wcsoft.utils;

public class Constants {

	public static final String Q_OK = "查询成功";
	public static final String P_OK = "操作成功";
	
	public static final String ACCESS_TOKEN = "accessToken";
	public static final String ORDER_COURIER_NOTIFY = "orderCourierNotify";//配送员通知订阅模板
	public static final String COURIER_URL = "courierUrl"; //员工录入地址
	public static final String WX_ENV_VERSION = "wxEnvVersion";//微信接口调用版本正式版为 "release"，体验版为 "trial"，开发版为 "develop"。默认是正式版。
	
	
	public static final String JMS_TOKEN = "";
	
	public static class UserTaskStatus{
		public static final String PAID = "0";//已打款
		public static final String FAILED = "1";//任务失败
		public static final String MISSION = "2";//任务中
		public static final String AUDIT = "3";//提交待审核
		public static final String REFUSE = "4";//审核拒绝待重新提交
		public static final String COMPLETE = "5";//任务成功待打款
	}

	public static class Status{
		public static final String NORMAL = "0";
		public static final String ERROR = "1";
	}
}
