package com.library.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Borrow)实体类
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
public class Borrow implements Serializable {
    private static final long serialVersionUID = 229291993611845453L;
    
    private Integer borrowId;
    
    private Date borrowStartTime;
    
    private Integer userId;
    
    private Integer bookId;
    
    private Integer bookCount;
    
    private Date borrowTime;


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

}