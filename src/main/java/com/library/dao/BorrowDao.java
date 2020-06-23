package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Borrow;
import com.library.entity.BorrowVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Borrow)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
public interface BorrowDao extends BaseDao<BorrowVo>{

    /**
     * 通过ID查询单条数据
     *
     * @param borrowId 主键
     * @return 实例对象
     */
    Borrow queryById(Integer borrowId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Borrow> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param borrow 实例对象
     * @return 对象列表
     */
    List<Borrow> queryAll(Borrow borrow);

    /**
     * 新增数据
     *
     * @param borrow 实例对象
     * @return 影响行数
     */
    int insert(Borrow borrow);

    /**
     * 修改数据
     *
     * @param borrow 实例对象
     * @return 影响行数
     */
    int update(Borrow borrow);

    /**
     * 通过主键删除数据
     *
     * @param borrowId 主键
     * @return 影响行数
     */
    int deleteById(@Param("borrowIds") List<Integer> borrowIds);

    void deleteByBookIdAndUserId(@Param("bookId") Integer bookId, @Param("userId")Integer userId);

    Borrow selectByBookIdAndUserId(@Param("bookId") Integer bookId, @Param("userId")Integer userId);
}