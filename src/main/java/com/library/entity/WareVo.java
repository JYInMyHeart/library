package com.library.entity;

/**
 * @Author:
 * @File: WareVo
 * @Time: 13:56 2020/6/9
 */
public class WareVo {
    private Integer wareId;

    private Integer wareCount;

    private Integer wareTotal;

    private Integer bookId;

    private String bookName;

    public Integer getWareId() {
        return wareId;
    }

    public void setWareId(Integer wareId) {
        this.wareId = wareId;
    }

    public Integer getWareCount() {
        return wareCount;
    }

    public void setWareCount(Integer wareCount) {
        this.wareCount = wareCount;
    }

    public Integer getWareTotal() {
        return wareTotal;
    }

    public void setWareTotal(Integer wareTotal) {
        this.wareTotal = wareTotal;
    }

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
}
