package com.xuan.rabbitmq.exchange.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/8 16:26
 * @description topic交换机 消费者
 */
public class ReceiveLogs1 {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = "Q1";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, "*.orange.*");
        System.out.println("等待接收消息");
        channel.basicConsume(queueName, true, (consumerTag, message) -> {
            System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
            System.out.println("接收队列：" + queueName + " 绑定键：" + message.getEnvelope().getRoutingKey());
        }, consumerTag -> {
        });
    }
}
