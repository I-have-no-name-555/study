package com.xuan.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;
import com.xuan.rabbitmq.util.SleepUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/3 19:12
 * @description 手动应答的工作线程
 */
public class AckWorker01 {
    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        System.out.println("worker1 等待接收消息时间较短");

        channel.basicQos(2);
        channel.basicConsume(QUEUE_NAME, false,
                (consumerTag, message) -> {
                    SleepUtil.sleep(1);
                    System.out.println("worker1 接收消息： " + new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                },
                System.out::println);
    }

}
