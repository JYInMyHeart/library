package com.library.controller;

import com.library.entity.RolePermission;
import com.library.service.RolePermissionService;
import com.library.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RolePermission)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
@Api(tags = {"rolePermission"}, value = "授权管理")
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

    @ApiOperation("授权接口")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody List<RolePermission> list) {
        if(!CollectionUtils.isEmpty(list)){
            list.stream().findFirst().ifPresent(r -> rolePermissionService.deleteById(r.getRoleId()));
        }
        for(RolePermission rp:list){
            rolePermissionService.insert(rp);
        }
        return ResponseEntity.ok();
    }

}