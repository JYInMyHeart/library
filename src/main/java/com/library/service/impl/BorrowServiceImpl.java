package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.dao.WareDao;
import com.library.entity.Book;
import com.library.entity.Borrow;
import com.library.dao.BorrowDao;
import com.library.entity.BorrowVo;
import com.library.entity.PageBean;
import com.library.entity.Ware;
import com.library.service.BorrowService;
import com.library.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * (Borrow)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowDao borrowDao;

    @Resource
    private WareDao wareDao;

    /**
     * 通过ID查询单条数据
     *
     * @param borrowId 主键
     * @return 实例对象
     */
    @Override
    public Borrow queryById(Integer borrowId) {
        return this.borrowDao.queryById(borrowId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Borrow> queryAllByLimit(int offset, int limit) {
        return this.borrowDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    @Override
    public Borrow insert(Borrow borrow) {
        Ware ware = wareDao.queryByBookId(borrow.getBookId());
        if (ware.getWareCount() >= borrow.getBookCount()) {
            this.borrowDao.insert(borrow);
            ware.setWareCount(ware.getWareCount() - borrow.getBookCount());
            wareDao.update(ware);
            return borrow;
        }
        return null;
    }

    /**
     * 修改数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    @Override
    public Borrow update(Borrow borrow) {
        this.borrowDao.update(borrow);
        return this.queryById(borrow.getBorrowId());
    }

    /**
     * 通过主键删除数据
     *
     * @param borrowId 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(List<Integer> borrowId) {
        int[] bookId = new int[1];
        Integer sum =
                borrowId.stream()
                        .map(b -> {
                            Borrow borrow = borrowDao.queryById(b);
                            bookId[0] = borrow.getBookId();
                            return borrow.getBookCount();
                        })
                        .reduce(Integer::sum)
                        .orElse(0);
        Ware ware = wareDao.queryByBookId(bookId[0]);
        ware.setWareCount(ware.getWareCount() + sum);
        wareDao.update(ware);
        borrowDao.deleteById(borrowId);
    }

    @Override
    public PageBean<BorrowVo> selectBorrowByPage(int page, int limit, String sort, String asc, String keyword) {
        PageUtil<BorrowVo, BorrowDao> pageUtil = new PageUtil<>(borrowDao);
        return pageUtil.helper(page, limit, sort, asc, keyword);
    }
}