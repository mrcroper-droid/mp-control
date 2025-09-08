package com.wcsoft.entity;

import java.io.Serializable;

public class AppMenu implements Serializable {
    private String id;

    private String pId;

    private String appId;

    private String menuName;

    private String menuType;

    private String menuKey;

    private String url;

    private String pagePath;

    private String appIdDes;

    private String content;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getAppIdDes() {
        return appIdDes;
    }

    public void setAppIdDes(String appIdDes) {
        this.appIdDes = appIdDes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pId=").append(pId);
        sb.append(", appId=").append(appId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuType=").append(menuType);
        sb.append(", menuKey=").append(menuKey);
        sb.append(", url=").append(url);
        sb.append(", pagePath=").append(pagePath);
        sb.append(", appIdDes=").append(appIdDes);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}