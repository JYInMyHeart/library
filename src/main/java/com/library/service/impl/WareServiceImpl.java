package com.library.service.impl;

import com.library.dao.UserDao;
import com.library.entity.PageBean;
import com.library.entity.UserVo;
import com.library.entity.Ware;
import com.library.dao.WareDao;
import com.library.entity.WareVo;
import com.library.service.WareService;
import com.library.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Ware)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
@Service("wareService")
public class WareServiceImpl implements WareService {
    @Resource
    private WareDao wareDao;

    /**
     * 通过ID查询单条数据
     *
     * @param wareId 主键
     * @return 实例对象
     */
    @Override
    public Ware queryById(Integer wareId) {
        return this.wareDao.queryById(wareId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Ware> queryAllByLimit(int offset, int limit) {
        return this.wareDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ware 实例对象
     * @return 实例对象
     */
    @Override
    public Ware insert(Ware ware) {
        this.wareDao.insert(ware);
        return ware;
    }

    /**
     * 修改数据
     *
     * @param ware 实例对象
     * @return 实例对象
     */
    @Override
    public Ware update(Ware ware) {
        this.wareDao.update(ware);
        return this.queryById(ware.getWareId());
    }

    /**
     * 通过主键删除数据
     *
     * @param wareId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(List<Integer> wareId) {
        return this.wareDao.deleteById(wareId) > 0;
    }

    @Override
    public PageBean<WareVo> selectWareByPage(int page, int limit, String sort, String asc, String keyword) {
        PageUtil<WareVo, WareDao> pageUtil = new PageUtil<>(wareDao);
        return pageUtil.helper(page,limit,sort,asc,keyword);
    }
}