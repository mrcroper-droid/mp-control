package com.wcsoft.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class App implements Serializable {
    private String id;
    private String appId;
    private String appSecret;
    private String name;
    private String setFlag;
    private Integer secondReplyTime;
    private String followReply;
    private String originAppId;
    private String userId;

    private static final long serialVersionUID = 1L;

    
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetFlag() {
        return setFlag;
    }

    public void setSetFlag(String setFlag) {
        this.setFlag = setFlag;
    }

    public Integer getSecondReplyTime() {
        return secondReplyTime;
    }

    public void setSecondReplyTime(Integer secondReplyTime) {
        this.secondReplyTime = secondReplyTime;
    }

    public String getFollowReply() {
        return followReply;
    }

    public void setFollowReply(String followReply) {
        this.followReply = followReply;
    }

    
    
    public String getOriginAppId() {
		return originAppId;
	}

	public void setOriginAppId(String originAppId) {
		this.originAppId = originAppId;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appId=").append(appId);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", name=").append(name);
        sb.append(", setFlag=").append(setFlag);
        sb.append(", secondReplyTime=").append(secondReplyTime);
        sb.append(", followReply=").append(followReply);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}