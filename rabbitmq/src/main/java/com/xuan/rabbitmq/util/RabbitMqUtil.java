package com.xuan.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/2 17:29
 * @description
 */
public class RabbitMqUtil {
    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("1.116.122.240");
        connectionFactory.setUsername("xuan");
        connectionFactory.setPassword("xuan");

        Connection connection = connectionFactory.newConnection();

        return connection.createChannel();
    }
}
