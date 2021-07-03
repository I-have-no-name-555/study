package com.xuan.service;

import com.xuan.bean.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author :Xuan
 * @date :Create in 2021/4/15 17:32
 * @description 自定义的类型转换器
 */
@Component
public class MyStringToBookConverter implements Converter<String, Book> {
    @Override
    public Book convert(String source) {
        String[] vars = source.split("-");
        Book book = new Book();
        book.setBookName(vars[0]);
        book.setAuthor(vars[1]);
        book.setPrice(Double.valueOf(vars[2]));
        book.setSales(Integer.valueOf(vars[3]));
        book.setStock(Integer.valueOf(vars[4]));
        return book;
    }
}
