package com.xuan.rabbitmq.dead;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/8 17:16
 * @description 死信队列 死信消费者
 */
public class DeadConsumer {
    public static final String DEAD_QUEUE_NAME = "dead_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();

        channel.basicConsume(DEAD_QUEUE_NAME,true,(consumerTag, message) ->
                System.out.println("死信消费者接收消息：" + new String(message.getBody(), StandardCharsets.UTF_8)),
                consumerTag -> {});
    }


}
