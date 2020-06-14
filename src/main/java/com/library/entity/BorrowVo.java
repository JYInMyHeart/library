package com.library.entity;

import java.util.Date;

/**
 * @Author: alex
 * @File: BorrowVo
 * @Time: 11:55 2020/6/13
 */
public class BorrowVo {
    private Integer borrowId;

    private Date borrowStartTime;

    private Integer userId;

    private Integer bookId;

    private Integer bookCount;

    private Date borrowTime;

    private String bookName;

    private String bookTag;

    private String bookAuthor;

    private Date bookPublishTime;

    private String bookBarPath;

    private String bookContent;

    private String userName;

    private Integer wareCount;

    private Integer wareTotal;

    public Integer getWareTotal() {
        return wareTotal;
    }

    public void setWareTotal(Integer wareTotal) {
        this.wareTotal = wareTotal;
    }

    public Integer getWareCount() {
        return wareCount;
    }

    public void setWareCount(Integer wareCount) {
        this.wareCount = wareCount;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Date getBorrowStartTime() {
        return borrowStartTime;
    }

    public void setBorrowStartTime(Date borrowStartTime) {
        this.borrowStartTime = borrowStartTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
