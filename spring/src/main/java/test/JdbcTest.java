package test;

import bean.Book;
import dao.BookDao;
import dao.impl.BookDaoImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import service.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 18:42
 * @description
 */
public class JdbcTest {
    @Test
    public void test1() {
        BookService bookService = new ClassPathXmlApplicationContext("bean14.xml")
                .getBean("bookService", BookService.class);
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("java");
        book.setBookStatus("a");
        bookService.addBook(book);
    }

    @Test
    public void test2() {
        System.out.println(new ClassPathXmlApplicationContext("bean14.xml")
                .getBean("bookService", BookService.class).findOne(1));
    }

    @Test
    public void test3() {
        System.out.println(new ClassPathXmlApplicationContext("bean14.xml")
                .getBean("bookService", BookService.class).findAll());
    }

    @Test
    public void test4() {
        BookService bookService = new ClassPathXmlApplicationContext("bean14.xml")
                .getBean("bookService", BookService.class);
        List<Object[]> list = new ArrayList<>();
        Object[] o1 = {2,"java","a"};
        Object[] o2 = {3,"c++","b"};
        Object[] o3 = {4,"MySQL","c"};
        list.add(o1);
        list.add(o2);
        list.add(o3);
        bookService.batchAdd(list);
    }

}
