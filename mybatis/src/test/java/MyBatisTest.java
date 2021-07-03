import com.xuan.Const;
import com.xuan.bean.Employee;
import com.xuan.bean.Teacher;
import com.xuan.dao.EmployeeDao;
import com.xuan.dao.KeyDao;
import com.xuan.dao.LockDao;
import com.xuan.dao.TeacherDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * @author :Xuan
 * @date :Create in 2021/4/20 16:43
 * @description
 */
public class MyBatisTest {
    private SqlSession sqlSession() throws IOException {
        InputStream is = Resources.getResourceAsStream(Const.MYBATIS_GLOBAL_CONFIG_RESOURCE);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory.openSession(true);
    }

    @Test
    public void curdTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee employee = employeeDao.getEmployeeById(9);
        System.out.println(employee);

//        employee.setEmpName("小王");
//        System.out.println(employeeDao.updateEmployee(employee));
//
//        employee.setEmpName("小明");
//        System.out.println(employeeDao.insertEmployee(employee));
//
//        System.out.println(employeeDao.deleteEmployee(1));

        sqlSession.close();
    }

    @Test
    public void listTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        System.out.println(employeeDao.getAllEmployees());
        sqlSession.close();
    }

    @Test
    public void oneMapTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        System.out.println(employeeDao.getEmployeeByIdReturnMap(9));
        sqlSession.close();
    }

    @Test
    public void mapTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        System.out.println(employeeDao.getAllEmployeeMap());
        sqlSession.close();
    }

    @Test
    public void joinTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        KeyDao keyDao = sqlSession.getMapper(KeyDao.class);
        System.out.println(keyDao.getKeyById(1));
        sqlSession.close();
    }

    @Test
    public void joinCollectionTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        LockDao lockDao = sqlSession.getMapper(LockDao.class);
        System.out.println(lockDao.getLockById(2));
        sqlSession.close();
    }

    @Test
    public void associationTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        KeyDao keyDao = sqlSession.getMapper(KeyDao.class);
        System.out.println(keyDao.getKeyByIdSimple(1));
        sqlSession.close();
    }

    @Test
    public void associationCollectionTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        LockDao lockDao = sqlSession.getMapper(LockDao.class);
        System.out.println(lockDao.getLockByIdByStep(2));
        sqlSession.close();
    }

    @Test
    public void ifTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setTeacherName("%a%");
        teacher.setBirthDate(new Date());
        System.out.println(teacherDao.listTeacherByCondition(teacher));
        sqlSession.close();
    }

    @Test
    public void foreachTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        System.out.println(teacherDao.listTeacherIn(Arrays.asList(1, 2, 3, 4, 5)));
        sqlSession.close();
    }

    @Test
    public void chooseTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = new Teacher();
//        teacher.setId(1);
//        teacher.setTeacherName("%a%");
//        teacher.setBirthDate(new Date());
        System.out.println(teacherDao.listTeacherByConditionChoose(teacher));
        sqlSession.close();
    }

    @Test
    public void updateTest() throws IOException {
        SqlSession sqlSession = sqlSession();
        TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setTeacherName("小明");
        teacher.setBirthDate(new Date());
        System.out.println(teacherDao.updateTeacher(teacher));
        sqlSession.close();
    }

    @Test//测试一级缓存
    public void cache1Test() throws IOException {
        SqlSession sqlSession = sqlSession();
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee employee1 = employeeDao.getEmployeeById(9);
        System.out.println(employee1);
//        employeeDao.updateEmployee(new Employee());
//        sqlSession.clearCache();
        Employee employee2 = employeeDao.getEmployeeById(9);
        System.out.println(employee2);
        System.out.println(employee1 == employee2);
        sqlSession.close();
    }
    @Test//测试二级缓存
    public void cache2Test() throws IOException {
        SqlSession sqlSession1 = sqlSession();
        EmployeeDao employeeDao1 = sqlSession1.getMapper(EmployeeDao.class);
        Employee employee1 = employeeDao1.getEmployeeById(9);
        System.out.println(employee1);
        sqlSession1.close();
        SqlSession sqlSession2 = sqlSession();
        EmployeeDao employeeDao2 = sqlSession2.getMapper(EmployeeDao.class);
        Employee employee2 = employeeDao2.getEmployeeById(9);
        System.out.println(employee2);
        System.out.println(employee1 == employee2);
        sqlSession2.close();
    }
}
