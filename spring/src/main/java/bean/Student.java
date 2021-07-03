package bean;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author :Xuan
 * @date :Create in 2021/3/9 18:01
 * @description
 */
public class Student {
    private String[] courses;
    private List<String> list;
    private Map<String,String> map;
    private Set<String> set;
    private List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
}
