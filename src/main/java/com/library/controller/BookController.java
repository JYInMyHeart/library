package com.library.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.library.entity.Book;
import com.library.entity.PageBean;
import com.library.service.BookService;
import com.library.util.ResponseEntity;
import com.library.util.UploadDataListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Book)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:06
 */
@RestController
@Api(tags = {"book"}, value = "图书管理")
@RequestMapping("book")
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;


    @ApiOperation("注册/添加图书")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Book bookPojo) {
        bookService.insert(bookPojo);
        return ResponseEntity.ok();
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除图书")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("bookId") String bookId) {
        bookService.deleteById(Arrays.stream(bookId.split(","))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList()));
        return ResponseEntity.ok();
    }

    /**
     * 更新
     */
    @ApiOperation("修改图书信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Book book) {
        bookService.update(book);
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

        PageBean<Book> bookPageBean =
                bookService.selectBookByPage(page, limit, sort, asc, keyword);
        if (bookPageBean != null) {
            return ResponseEntity.data(bookPageBean);
        } else {
            return ResponseEntity.ok();
        }
    }

    @ApiOperation("条形码转Base64")
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

    @ApiOperation("文件上传接口")
    @PostMapping(value = "/uploadBook")
    public ResponseEntity uploadBook(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), Book.class, new UploadDataListener(bookService)).sheet().doRead();
            return ResponseEntity.data(Collections.singletonList("nice"));
        } catch (IOException e) {
            return ResponseEntity.serverInternalError();
        }
    }

}