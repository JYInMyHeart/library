package com.library.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -77370717296006433L;
    
    private Integer permissionId;
    
    private String permissionName;
    
    private String permissionPath;
    
    private Integer permissionLevel;
    
    private Integer permissionParent;


    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(String permissionPath) {
        this.permissionPath = permissionPath;
    }

    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public Integer getPermissionParent() {
        return permissionParent;
    }

    public void setPermissionParent(Integer permissionParent) {
        this.permissionParent = permissionParent;
    }

}