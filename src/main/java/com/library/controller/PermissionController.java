package com.library.controller;

import com.library.entity.PageBean;
import com.library.entity.Permission;
import com.library.service.PermissionService;
import com.library.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Permission)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
@RestController
@Api(tags = {"permission"}, value = "权限管理")
@RequestMapping("permission")
public class PermissionController {

    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    @ApiOperation("增加权限")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Permission permissionPojo) {
        permissionService.insert(permissionPojo);
        return ResponseEntity.ok();
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除权限")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("permissionId") Integer permissionId) {
        permissionService.deleteById(permissionId);
        return ResponseEntity.ok();
    }

    /**
     * 更新
     */
    @ApiOperation("编辑权限信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return ResponseEntity.ok();
    }


    @ApiOperation("权限列表")
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestParam(required = false) String keyword,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "sort", required = false, defaultValue = "") String sort
    ) {

        PageBean<Permission> permissionPageBean =
                permissionService.selectPermissionByPage(page, limit, sort, asc, keyword);
        if (permissionPageBean != null) {
            return ResponseEntity.data(permissionPageBean);
        } else {
            return ResponseEntity.ok();
        }
    }
}