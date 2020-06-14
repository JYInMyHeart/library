package com.library.service.impl;

import com.library.dao.UserDao;
import com.library.entity.Book;
import com.library.dao.BookDao;
import com.library.entity.PageBean;
import com.library.entity.UserVo;
import com.library.service.BookService;
import com.library.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 18:00:06
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    /**
     * 通过ID查询单条数据
     *
     * @param bookId 主键
     * @return 实例对象
     */
    @Override
    public Book queryById(Integer bookId) {
        return this.bookDao.queryById(bookId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Book> queryAllByLimit(int offset, int limit) {
        return this.bookDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book insert(Book book) {
        this.bookDao.insert(book);
        return book;
    }

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book update(Book book) {
        this.bookDao.update(book);
        return this.queryById(book.getBookId());
    }

    /**
     * 通过主键删除数据
     *
     * @param bookId 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(List<Integer> bookId) {
         this.bookDao.deleteById(bookId);
    }

    @Override
    public PageBean<Book> selectBookByPage(int page, int limit, String sort, String asc, String keyword) {
        PageUtil<Book, BookDao> pageUtil = new PageUtil<>(bookDao);
        return pageUtil.helper(page,limit,sort,asc,keyword);
    }

    @Override
    public void insertList(List<Book> list) {
        bookDao.insertList(list);
    }
}