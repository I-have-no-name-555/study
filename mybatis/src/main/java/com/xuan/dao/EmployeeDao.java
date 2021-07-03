package com.xuan.dao;

import com.xuan.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author :Xuan
 * @date :Create in 2021/4/20 9:58
 * @description
 */
public interface EmployeeDao {


    /**
     * 根据id返回Employee
     * @param id 查询依据
     * @return 查询得到的Employee，为null表示未找到
     */
    Employee getEmployeeById(Integer id);

    /**
     * 更新指定employee
     * @param employee 新的Employee
     * @return 影响的行数
     */
    int updateEmployee(Employee employee);

    /**
     * 根据id删除Employee
     * @param id 待删除实体的主键
     * @return  受影响的行数
     */
    int deleteEmployee(Integer id);

    /**
     * 新增一个实体
     * @param employee 待新增的实体
     * @return 影响的行数
     */
    int insertEmployee(Employee employee);

    /**
     * 测试命名参数，实际上没用
     * @param id id
     * @param empName empName
     * @return 查到的实体
     */
    Employee getEmployeeByIdAndName(@Param("id") Integer id,@Param("empName")String empName);

    /**
     * 查询所有的实体
     * @return 封装的List
     */
    List<Employee> getAllEmployees();

    /**
     * 根据id查询实体并以列名为键 属性值为值封装map
     * @param id id
     * @return 实体转换的map
     */
    Map<String,Object> getEmployeeByIdReturnMap(Integer id);

    /**
     * 将全部记录以 主键-实体 返回
     * @return 返回的map
     */
    @MapKey("id")
    Map<Integer,Employee> getAllEmployeeMap();
}
