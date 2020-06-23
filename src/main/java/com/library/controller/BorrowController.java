package com.library.controller;

import com.library.entity.BookLike;
import com.library.entity.Borrow;
import com.library.entity.Borrow;
import com.library.entity.BorrowVo;
import com.library.entity.PageBean;
import com.library.service.BorrowService;
import com.library.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Borrow)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
@RestController
@Api(tags = { "borrow" }, value = "借阅管理")
@RequestMapping("borrow")
public class BorrowController {
    /**
     * 服务对象
     */
    @Resource
    private BorrowService borrowService;

    @ApiOperation("注册/添加图书")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Borrow borrowPojo) {
        Borrow insert = borrowService.insert(borrowPojo);
        if (insert == null) {
            ResponseEntity.failed("存书不足");
        }
        return ResponseEntity.ok();
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除图书")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("borrowId") String borrowId) {
        borrowService.deleteById(
                Arrays.stream(borrowId.split(","))
                      .map(Integer::parseInt)
                      .collect(Collectors.toList()));
        return ResponseEntity.ok();
    }

    /**
     * 更新
     */
    @ApiOperation("修改图书信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Borrow borrow) {
        borrowService.update(borrow);
        return ResponseEntity.ok();
    }

    @ApiOperation("图书列表")
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestParam(required = false) String keyword,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "sort", required = false, defaultValue = "") String sort
    ) {

        PageBean<BorrowVo> borrowPageBean =
                borrowService.selectBorrowByPage(page, limit, sort, asc, keyword);
        if (borrowPageBean != null) {
            return ResponseEntity.data(borrowPageBean);
        } else {
            return ResponseEntity.ok();
        }
    }

    @ApiOperation("通过图书ID和用户ID删除")
    @PostMapping(value = "/deleteByBookIdAndUserId")
    public ResponseEntity deleteByBookIdAndUserId(@RequestBody BookLike bookLike) {
        borrowService.deleteByBookIdAndUserId(bookLike);
        return ResponseEntity.ok();
    }

    @ApiOperation("通过图书ID和用户ID查询记录")
    @PostMapping(value = "/selectByBookIdAndUserId")
    public ResponseEntity selectByBookIdAndUserId(@RequestBody BookLike bookLike) {
        Borrow borrow = borrowService.selectByBookIdAndUserId(bookLike);
        if (borrow != null) {
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.serverInternalError();
        }
    }

}