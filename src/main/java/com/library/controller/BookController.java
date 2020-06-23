package com.library.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.library.entity.Book;
import com.library.entity.BookLike;
import com.library.entity.PageBean;
import com.library.entity.User;
import com.library.service.BookService;
import com.library.util.ResponseEntity;
import com.library.util.UploadDataListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Book)表控制层
 *
 * @author makejava
 * @since 2020-06-08 18:00:06
 */
@RestController
@Api(tags = { "book" }, value = "图书管理")
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

    @ApiOperation("点赞")
    @PostMapping(value = "/like")
    public ResponseEntity like(@RequestBody BookLike bookLike) {
        bookService.like(bookLike);
        return ResponseEntity.ok();
    }

    @ApiOperation("取消点赞")
    @PostMapping(value = "/dislike")
    public ResponseEntity dislike(@RequestBody BookLike bookLike) {
        bookService.dislike(bookLike);
        return ResponseEntity.ok();
    }

    @ApiOperation("是否点赞")
    @PostMapping(value = "/islike")
    public ResponseEntity islike(@RequestBody BookLike bookLike) {
        boolean islike = bookService.islike(bookLike);
        return islike? ResponseEntity.ok() : ResponseEntity.serverInternalError();
    }

    @ApiOperation(value = "导出", notes = "导出")
    @GetMapping("/excel")
    public void excel(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("导出图书数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            PageBean<Book> bookPageBean =
                    bookService.selectBookByPage(1, 10000, null, null, null);
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), Book.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                     .doWrite(bookPageBean.getLists());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    @ApiOperation("文件下载")
    @GetMapping(value = "/downloadFile")
    public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }


}