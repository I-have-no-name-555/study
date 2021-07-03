package dao.impl;

import bean.Book;
import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 18:22
 * @description
 */
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Book book) {
        String sql = "insert into book values(?,?,?)";
        Object[] args = {book.getBookId(), book.getBookName(), book.getBookStatus()};
        System.out.println(jdbcTemplate.update(sql, args));
    }

    @Override
    public void batchAddBook(List<Object[]> batchArgs) {
        String sql = "insert into book values (?,?,?)";
        jdbcTemplate.batchUpdate(sql,batchArgs);
    }

    @Override
    public List<Book> findAllBook() {
        String sql = "select * from book";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book findBookInfo(int id) {
        String sql = "select * from book where bookId = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Book.class),id);
    }

    @Override
    public Integer selectCount() {
        return jdbcTemplate.queryForObject("select count(*) from book",Integer.class);
    }
}
