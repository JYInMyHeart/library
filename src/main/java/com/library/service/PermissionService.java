package com.library.service;

import com.library.entity.PageBean;
import com.library.entity.Permission;
import java.util.List;

/**
 * (Permission)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param permissionId 主键
     * @return 实例对象
     */
    Permission queryById(Integer permissionId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param permissionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer permissionId);

    Permission selectPermissionByName(String permission_name);

    PageBean<Permission> selectPermissionByPage(int page, int size, String sort, String asc, String keyword);
}