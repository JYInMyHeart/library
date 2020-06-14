package com.library.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.util.Date;
import java.io.Serializable;

/**
 * (Book)实体类
 *
 * @author makejava
 * @since 2020-06-08 18:00:03
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 187595675485115996L;


    private Integer bookId;
    @ExcelProperty("图书名")
    private String bookName;
    @ExcelProperty("分类")
    private String bookTag;
    @ExcelProperty("作者")
    private String bookAuthor;
    @ExcelProperty("发布时间")
    private Date bookPublishTime;
    @ExcelProperty("条形码")
   
    private String bookBarPath;
    
    private String bookContent;


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookTag() {
        return bookTag;
    }

    public void setBookTag(String bookTag) {
        this.bookTag = bookTag;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Date getBookPublishTime() {
        return bookPublishTime;
    }

    public void setBookPublishTime(Date bookPublishTime) {
        this.bookPublishTime = bookPublishTime;
    }

    public String getBookBarPath() {
        return bookBarPath;
    }

    public void setBookBarPath(String bookBarPath) {
        this.bookBarPath = bookBarPath;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

}