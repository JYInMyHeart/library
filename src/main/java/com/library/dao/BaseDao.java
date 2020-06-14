package com.library.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: alex
 * @File: BaseService
 * @Time: 11:09 2020/6/9
 */
public interface BaseDao<T> {
    List<T> selectByPage(Map<String,Object> map);
    int count(@Param("keyword") String keyword);
}
