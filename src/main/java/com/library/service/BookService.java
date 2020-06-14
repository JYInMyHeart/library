package com.library.service;

import com.library.entity.Book;
import com.library.entity.PageBean;

import java.util.List;

/**
 * (Book)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 18:00:05
 */
public interface BookService {

    /**
     * 通过ID查询单条数据
     *
     * @param bookId 主键
     * @return 实例对象
     */
    Book queryById(Integer bookId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Book> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book insert(Book book);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book update(Book book);

    /**
     * 通过主键删除数据
     *
     * @param bookId 主键
     */
    void deleteById(List<Integer> bookId);

    PageBean<Book> selectBookByPage(int page, int limit, String sort, String asc, String keyword);

    void insertList(List<Book> list);
}