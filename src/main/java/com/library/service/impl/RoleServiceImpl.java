package com.library.service.impl;

import com.library.dao.PermissionDao;
import com.library.entity.PageBean;
import com.library.entity.Permission;
import com.library.entity.Role;
import com.library.dao.RoleDao;
import com.library.entity.RoleVo;
import com.library.service.RoleService;
import com.library.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 18:00:07
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer roleId) {
        return this.roleDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(List<Integer> roleId) {
        return this.roleDao.deleteById(roleId) > 0;
    }

    @Override
    public PageBean<Role> selectRoleByPage(int page, int size, String sort, String asc, String keyword) {
        PageUtil<Role, RoleDao> pageUtil = new PageUtil<>(roleDao);
        return pageUtil.helper(page,size,sort,asc,keyword);
    }

    @Override
    public List<Role> queryAll() {
        Role role = new Role();
        return roleDao.queryAll(role);
    }

    @Override
    public List<RoleVo> selectPermissionByRoleId(Integer roleId){
        return roleDao.selectPermissionByRoleId(roleId);
    }
}