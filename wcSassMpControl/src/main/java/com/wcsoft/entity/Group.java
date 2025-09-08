package com.wcsoft.entity;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
    private String groupId;

    private String groupName;

    private Integer groupSort;

    private Date createDatetime;

    private Date updateDatetime;

    private String groupDesc;

    private String groupImg;

    private String groupRemark;

    private static final long serialVersionUID = 1L;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupSort() {
        return groupSort;
    }

    public void setGroupSort(Integer groupSort) {
        this.groupSort = groupSort;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public String getGroupRemark() {
        return groupRemark;
    }

    public void setGroupRemark(String groupRemark) {
        this.groupRemark = groupRemark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", groupSort=").append(groupSort);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", groupDesc=").append(groupDesc);
        sb.append(", groupImg=").append(groupImg);
        sb.append(", groupRemark=").append(groupRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}