package com.xuan.rabbitmq.dead;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/8 17:33
 * @description 死信队列 生产者
 */
public class Producer {
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder().expiration("10000").build();
        for (int i = 0; i < 101; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE_NAME,"zhangsan",
                    properties,
                    message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
