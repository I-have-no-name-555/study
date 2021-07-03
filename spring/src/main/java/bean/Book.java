package bean;

import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 演示使用set方法进行注入属性
 */
public class Book {
    //创建属性
    @Nullable
    private String bookName;
    private Integer bookId;
    private String bookStatus;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookId=" + bookId +
                ", bookStatus='" + bookStatus + '\'' +
                '}';
    }
}

