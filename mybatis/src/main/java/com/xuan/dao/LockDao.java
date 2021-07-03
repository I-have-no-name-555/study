package com.xuan.dao;

import com.xuan.bean.Lock;

/**
 * @author :Xuan
 * @date :Create in 2021/4/21 11:25
 * @description
 */
public interface LockDao {
    /**
     * 根据主键获得实体
     * @param id 主键
     * @return 实体
     */
    Lock getLockById(Integer id);
    /**
     * 根据主键获得实体 简化版
     * @param id 主键
     * @return 实体
     */
    Lock getLockByIdSimple(Integer id);
    /**
     * 根据主键获得实体 分步查询
     * @param id 主键
     * @return 实体
     */
    Lock getLockByIdByStep(Integer id);
}
