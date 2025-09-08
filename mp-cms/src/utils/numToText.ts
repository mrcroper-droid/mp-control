export default {
    VideoStatusToText: (status: string) => {
        let res
        switch (status) {
            case '0': res = { type: "success", text: "已打款" }; break;
            case '1': res = { type: "danger", text: "任务失败" }; break;
            case '2': res = { type: "info", text: "任务中" }; break;
            case '3': res = { type: "primary", text: "提交待审核" }; break;
            case '4': res = { type: "danger", text: "审核拒绝待重新提交" }; break;
            case '5': res = { type: "warning", text: "任务成功待打款" }; break;
            default: break;
        }
        return res
    }
}

// "0";//已打款
//   public static final String FAILED = "1";//任务失败
//   public static final String MISSION = "2";//任务中
//   public static final String AUDIT = "3";//提交待审核
//   public static final String REFUSE = "4";//审核拒绝待重新提交
//   public static final String COMPLETE = "5";//任务成功待打款
//  }