package com.library.entity;

import java.io.Serializable;

/**
 * (Ware)实体类
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
public class Ware implements Serializable {
    private static final long serialVersionUID = 760711243031159570L;
    
    private Integer wareId;
    
    private Integer wareCount;
    
    private Integer wareTotal;
    
    private Integer bookId;


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

}