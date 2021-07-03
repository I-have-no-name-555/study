package dao;

import bean.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 18:22
 * @description
 */
@Repository
public interface BookDao {

    void add(Book book);

    Integer selectCount();

    Book findBookInfo(int id);

    List<Book> findAllBook();

    void batchAddBook(List<Object[]> batchArgs);
}
