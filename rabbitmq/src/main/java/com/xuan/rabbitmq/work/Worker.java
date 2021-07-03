package com.xuan.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/2 17:33
 * @description 工作线程
 */
public class Worker {
    public static final String QUEUE_NAME = "hello";

    public static void consume()  {
        try {
            Channel channel = RabbitMqUtil.getChannel();
            channel.basicConsume(QUEUE_NAME,true,
                    (consumerTag, message) -> System.out.println(Thread.currentThread().getName() + "接收消息： " + new String(message.getBody())),
                    System.out::println);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new Thread(Worker::consume,"C1").start();
        new Thread(Worker::consume,"C2").start();
    }
}
