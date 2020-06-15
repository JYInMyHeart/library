package com.library.service;

import com.library.entity.PageBean;
import com.library.entity.Permission;
import com.library.entity.User;
import com.library.entity.UserVo;

import java.util.List;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(List<Integer> userId);

    /**
     * 选择性查询
     *
     * @param user User对象
     * @return User
     */
    User querySelective(User user);

    PageBean<UserVo> selectUserByPage(int page, int size, String sort, String asc, String keyword);

    List<Permission> getMenu(String userAccount);

    Map<String, Integer> count();
}