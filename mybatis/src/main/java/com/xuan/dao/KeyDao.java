package com.xuan.dao;

import com.xuan.bean.Key;

import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/4/21 10:56
 * @description
 */
public interface KeyDao {
    /**
     * 根据主键获取实体
     * @param id 主键
     * @return 实体
     */
    Key getKeyById(Integer id);
    /**
     * 根据主键获得实体 简化版
     * @param id 主键
     * @return 实体
     */
    Key getKeyByIdSimple(Integer id);

    /**
     * 根据lockId得到一个KeyList
     * @param lockId lockId
     * @return 得到的List
     */
    List<Key> getKeysByLockId(Integer lockId);
}
