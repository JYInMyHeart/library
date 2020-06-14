package com.library.entity;

/**
 * @Author: alex
 * @File: RoleVo
 * @Time: 12:10 2020/6/9
 */
public class RoleVo {
    private Integer roleId;
    private String roleName;
    private Integer permissionId;
    private String permissionName;

    private String permissionPath;

    private Integer permissionLevel;

    private Integer permissionParent;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

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
