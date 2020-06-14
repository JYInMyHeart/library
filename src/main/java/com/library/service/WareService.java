package com.library.service;

import com.library.entity.PageBean;
import com.library.entity.Ware;
import com.library.entity.WareVo;

import java.util.List;

/**
 * (Ware)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
public interface WareService {

    /**
     * 通过ID查询单条数据
     *
     * @param wareId 主键
     * @return 实例对象
     */
    Ware queryById(Integer wareId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Ware> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ware 实例对象
     * @return 实例对象
     */
    Ware insert(Ware ware);

    /**
     * 修改数据
     *
     * @param ware 实例对象
     * @return 实例对象
     */
    Ware update(Ware ware);

    /**
     * 通过主键删除数据
     *
     * @param wareId 主键
     * @return 是否成功
     */
    boolean deleteById(List<Integer> wareId);

    PageBean<WareVo> selectWareByPage(int page, int limit, String sort, String asc, String keyword);
}