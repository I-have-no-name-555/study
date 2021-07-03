package com.xuan.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/2 16:53
 * @description 消费者
 */
public class Consumer {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.basicConsume(QUEUE_NAME, true,
                ((consumerTag, message) -> System.out.println(message)),
                (consumerTag -> System.out.println("消费中断")));
    }
}
