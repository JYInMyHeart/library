package com.library.controller;

import com.library.entity.PageBean;
import com.library.entity.Role;
import com.library.entity.Role;
import com.library.entity.RoleVo;
import com.library.service.RoleService;
import com.library.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
@RestController
@Api(tags = {"role"}, value = "角色管理")
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    @ApiOperation("增加角色")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Role rolePojo) {
        roleService.insert(rolePojo);
        return ResponseEntity.ok();
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除角色")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("roleId") String roleId) {
        roleService.deleteById(Arrays.stream(roleId.split(","))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList()));
        return ResponseEntity.ok();
    }

    /**
     * 更新
     */
    @ApiOperation("编辑角色信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Role role) {
        roleService.update(role);
        return ResponseEntity.ok();
    }


    @ApiOperation("带分页的角色列表")
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestParam(required = false) String keyword,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "sort", required = false, defaultValue = "") String sort
    ) {

        PageBean<Role> rolePageBean =
                roleService.selectRoleByPage(page, limit, sort, asc, keyword);
        if (rolePageBean != null) {
            return ResponseEntity.data(rolePageBean);
        } else {
            return ResponseEntity.ok();
        }
    }

    @ApiOperation("角色列表")
    @GetMapping(value= "/listAll")
    public ResponseEntity listAll(){
        List<Role> list = roleService.queryAll();
        return ResponseEntity.data(list);
    }

    @ApiOperation("通过角色Id查询权限列表")
    @GetMapping(value= "/listByRoleId")
    public ResponseEntity selectPermissionByRoleId(@RequestParam Integer roleId){
        List<RoleVo> list = roleService.selectPermissionByRoleId(roleId);
        return ResponseEntity.data(list);
    }
}