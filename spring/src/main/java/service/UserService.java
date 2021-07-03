package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author :Xuan
 * @date :Create in 2021/3/9 17:14
 * @description
 */
@Service(value = "userService")
@Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED
,timeout = 1,readOnly = false,rollbackFor = NullPointerException.class)
public class UserService {
    @Autowired
    private UserDao userDao;
    public void accountMoney(){
            userDao.reduceMoney();
            userDao.addMoney();
    }

}
