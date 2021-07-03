package service;

import bean.Book;
import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 18:22
 * @description
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void addBook(Book book){
        bookDao.add(book);
    }
    public int findCount(){
        return bookDao.selectCount();
    }
    public Book findOne(int id){
        return bookDao.findBookInfo(id);
    }
    public List<Book> findAll(){
        return bookDao.findAllBook();
    }
    public void batchAdd(List<Object[]> batchArgs){
        bookDao.batchAddBook(batchArgs);
    }
}
