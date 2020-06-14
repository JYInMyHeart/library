package com.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library.entity.PageBean;
import com.library.entity.Permission;
import com.library.entity.User;
import com.library.entity.UserVo;
import com.library.service.UserService;
import com.library.util.JwtToken;
import com.library.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
@RestController
@Api(tags = { "user" }, value = "用户管理")
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        ResponseEntity responseEntity = ResponseEntity.ok();
        try {
            User u = userService.querySelective(user);
            if (u != null && u.getUserPassword().equals(user.getUserPassword())) {
                String token = JwtToken.sign(u, 60L * 1000L * 30L);
                responseEntity.putDataValue("username", u.getUserName());
                responseEntity.putDataValue("userAccount", u.getUserAccount());
                responseEntity.putDataValue("token", token);
                responseEntity.putDataValue("userRole", u.getRoleId());
                responseEntity.putDataValue("userLogo", u.getUserLogo());
                responseEntity.putDataValue("userId", u.getUserId());
            } else {
                responseEntity = ResponseEntity.customerError();
                responseEntity.putDataValue("msg", "账号密码错误");
            }
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            responseEntity = ResponseEntity.customerError();
            responseEntity.putDataValue("msg", "账号密码错误");
        }
        return responseEntity;
    }

    @ApiOperation("注册/添加用户")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody User userPojo) {
        userService.insert(userPojo);
        return ResponseEntity.ok();
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除用户")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("userId") String userId) {
        userService.deleteById(Arrays.stream(userId.split(","))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList()));
        return ResponseEntity.ok();
    }

    /**
     * 更新
     */
    @ApiOperation("修改用户信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok();
    }

    @ApiOperation("用户列表")
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestParam(required = false) String keyword,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "sort", required = false, defaultValue = "") String sort
    ) {

        PageBean<UserVo> userPageBean =
                userService.selectUserByPage(page, limit, sort, asc, keyword);
        if (userPageBean != null) {
            return ResponseEntity.data(userPageBean);
        } else {
            return ResponseEntity.ok();
        }
    }

    @GetMapping(value = "/menu")
    public ResponseEntity menu(@RequestParam String userAccount) {
        List<Permission> menu = userService.getMenu(userAccount);
        return ResponseEntity.data(menu);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity upload(MultipartFile file) {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String imgStr = encoder.encode(file.getBytes());
            return ResponseEntity.logo(imgStr);
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.serverInternalError();
        }
    }

}