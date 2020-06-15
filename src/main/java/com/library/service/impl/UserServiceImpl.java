package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.dao.BorrowDao;
import com.library.dao.PermissionDao;
import com.library.dao.WareDao;
import com.library.entity.PageBean;
import com.library.entity.Permission;
import com.library.entity.User;
import com.library.dao.UserDao;
import com.library.entity.UserVo;
import com.library.service.UserService;
import com.library.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-06-08 18:00:08
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private BookDao bookDao;
    @Resource
    private WareDao wareDao;
    @Resource
    private BorrowDao borrowDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userId) {
        return this.userDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(List<Integer> userId) {
        return this.userDao.deleteById(userId) > 0;
    }

    @Override
    public User querySelective(User user) {
        List<User> userList = userDao.queryAll(user);
        if(!CollectionUtils.isEmpty(userList)){
            return userList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public PageBean<UserVo> selectUserByPage(int page, int size, String sort, String asc, String keyword) {
        PageUtil<UserVo, UserDao> pageUtil = new PageUtil<>(userDao);
        return pageUtil.helper(page,size,sort,asc,keyword);
    }

    @Override
    public List<Permission> getMenu(String userAccount) {
        return userDao.getMenu(userAccount);
    }

    @Override
    public Map<String, Integer> count() {
        Map<String, Integer> map = new HashMap<>();
        int userCount = userDao.count(null);
        int bookCount = bookDao.count(null);
        int borrowCount = borrowDao.count(null);
        int wareCount = wareDao.count(null);
        map.put("userCount",userCount);
        map.put("bookCount",bookCount);
        map.put("borrowCount",borrowCount);
        map.put("wareCount",wareCount);
        return map;
    }
}