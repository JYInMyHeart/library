package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Ware;
import com.library.entity.WareVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Ware)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
public interface WareDao extends BaseDao<WareVo>{

    /**
     * 通过ID查询单条数据
     *
     * @param wareId 主键
     * @return 实例对象
     */
    Ware queryById(Integer wareId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Ware> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ware 实例对象
     * @return 对象列表
     */
    List<Ware> queryAll(Ware ware);

    /**
     * 新增数据
     *
     * @param ware 实例对象
     * @return 影响行数
     */
    int insert(Ware ware);

    /**
     * 修改数据
     *
     * @param ware 实例对象
     * @return 影响行数
     */
    int update(Ware ware);

    /**
     * 通过主键删除数据
     *
     * @param wareId 主键
     * @return 影响行数
     */
    int deleteById(@Param("wareIds") List<Integer> wareIds);

    Ware queryByBookId(Integer bookId);
}