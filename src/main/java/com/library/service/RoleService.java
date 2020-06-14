package com.library.service;

import com.library.entity.PageBean;
import com.library.entity.Role;
import com.library.entity.RoleVo;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    Role queryById(Integer roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(List<Integer> roleId);

    PageBean<Role> selectRoleByPage(int page, int size, String sort, String asc, String keyword);

    List<Role> queryAll();

    List<RoleVo> selectPermissionByRoleId(Integer roleId);
}