package com.library.controller;

import com.library.entity.Ware;
import com.library.entity.PageBean;
import com.library.entity.WareVo;
import com.library.service.WareService;
import com.library.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Ware)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
@RestController
@Api(tags = { "ware" }, value = "库存管理")
@RequestMapping("ware")
public class WareController {
    /**
     * 服务对象
     */
    @Resource
    private WareService wareService;

    @ApiOperation("注册/添加库存")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Ware warePojo) {
        wareService.insert(warePojo);
        return ResponseEntity.ok();
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除库存")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("wareId") String wareId) {
        wareService.deleteById(Arrays.stream(wareId.split(","))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList()));
        return ResponseEntity.ok();
    }

    /**
     * 更新
     */
    @ApiOperation("修改库存信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Ware ware) {
        wareService.update(ware);
        return ResponseEntity.ok();
    }

    @ApiOperation("库存列表")
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestParam(required = false) String keyword,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "sort", required = false, defaultValue = "") String sort
    ) {

        PageBean<WareVo> warePageBean =
                wareService.selectWareByPage(page, limit, sort, asc, keyword);
        if (warePageBean != null) {
            return ResponseEntity.data(warePageBean);
        } else {
            return ResponseEntity.ok();
        }
    }

}