package com.wcsoft.entity;

import java.io.Serializable;

public class AppReply implements Serializable {
    private String id;

    private String appId;

    private String msgType;

    private String title;

    private String appIdDes;

    private String thumbMediaId;

    private String mediaId;

    private String content;

    private String pagePath;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppIdDes() {
        return appIdDes;
    }

    public void setAppIdDes(String appIdDes) {
        this.appIdDes = appIdDes;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appId=").append(appId);
        sb.append(", msgType=").append(msgType);
        sb.append(", title=").append(title);
        sb.append(", appIdDes=").append(appIdDes);
        sb.append(", thumbMediaId=").append(thumbMediaId);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", content=").append(content);
        sb.append(", pagePath=").append(pagePath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}