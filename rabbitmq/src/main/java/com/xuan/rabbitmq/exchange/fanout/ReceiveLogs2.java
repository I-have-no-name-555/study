package com.xuan.rabbitmq.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/6 17:20
 * @description fanout交换机 消费者
 */
public class ReceiveLogs2 {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println("等待接收消息");
        channel.basicConsume(queueName, true,
                (consumerTag, message) -> System.out.println("消费者2接收消息：" + new String(message.getBody(), StandardCharsets.UTF_8)),
                consumerTag -> {
                });
    }
}
