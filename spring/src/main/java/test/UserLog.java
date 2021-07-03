package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :Xuan
 * @date :Create in 2021/3/23 8:40
 * @description
 */
public class UserLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLog.class);

    public static void main(String[] args) {
        LOGGER.info("hello");
        LOGGER.warn("warn");
    }
}
