package dao.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author :Xuan
 * @date :Create in 2021/3/9 17:16
 * @description
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addMoney() {
        String sql = "update account set money = money + ? where username = ?";
        jdbcTemplate.update(sql,100,"mary");
    }

    @Override
    public void reduceMoney() {
        String sql = "update account set money = money - ? where username = ?";
        jdbcTemplate.update(sql,100,"lucy");
    }
}
