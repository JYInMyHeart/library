package com.library.service;

import com.library.entity.Borrow;
import com.library.entity.BorrowVo;
import com.library.entity.PageBean;

import java.util.List;

/**
 * (Borrow)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
public interface BorrowService {

    /**
     * 通过ID查询单条数据
     *
     * @param borrowId 主键
     * @return 实例对象
     */
    Borrow queryById(Integer borrowId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Borrow> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    Borrow insert(Borrow borrow);

    /**
     * 修改数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    Borrow update(Borrow borrow);

    /**
     * 通过主键删除数据
     *
     * @param borrowId 主键
     */
    void deleteById(List<Integer> borrowId);

    PageBean<BorrowVo> selectBorrowByPage(int page, int limit, String sort, String asc, String keyword);
}