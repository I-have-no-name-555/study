package com.xuan.dao;

import com.xuan.bean.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/4/21 17:10
 * @description
 */
public interface TeacherDao {
    /**
     * 根据主键获得实体
     * @param id 主键
     * @return 实体
     */
    Teacher getTeacherById(Integer id);

    /**
     * 根据传入的teacher具有的属性查找对应实体列表
     * @param teacher 查询条件
     * @return 查询到的列表
     */
    List<Teacher> listTeacherByCondition(Teacher teacher);

    /**
     * 根据传入的多个主键查询对应的实体
     * @param ids 传入的主键
     * @return 实体集合
     */
    List<Teacher> listTeacherIn(@Param("ids") List<Integer> ids);
    /**
     * 根据传入的teacher具有的属性查找对应实体列表 使用choose标签
     * @param teacher 查询条件
     * @return 查询到的列表
     */
    List<Teacher> listTeacherByConditionChoose(Teacher teacher);

    /**
     * 根据id更新实体数据
     * @param teacher 实体
     * @return 影响的行数
     */
    int updateTeacher(Teacher teacher);
}
