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
 * @description 死信队列 普通消费者
 */
public class NormalConsumer {
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange";
    public static final String DEAD_EXCHANGE_NAME = "dead_exchange";
    public static final String NORMAL_QUEUE_NAME = "normal_queue";
    public static final String DEAD_QUEUE_NAME = "dead_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtil.getChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE_NAME);
        arguments.put("x-dead-letter-routing-key","lisi");
        arguments.put("x-max-length",6);

        channel.queueDeclare(NORMAL_QUEUE_NAME,false,false,false, arguments);
        channel.queueDeclare(DEAD_QUEUE_NAME,false,false,false,null);

        channel.queueBind(NORMAL_QUEUE_NAME,NORMAL_EXCHANGE_NAME,"zhangsan");
        channel.queueBind(DEAD_QUEUE_NAME,DEAD_EXCHANGE_NAME,"lisi");

        channel.basicConsume(NORMAL_QUEUE_NAME,false,(consumerTag, message) ->{
                    channel.basicReject(message.getEnvelope().getDeliveryTag(),false);
                    System.out.println("普通消费者接收消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
                }, consumerTag -> {});
    }


}
