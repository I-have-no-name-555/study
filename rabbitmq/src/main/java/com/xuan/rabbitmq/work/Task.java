package com.xuan.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/3 15:33
 * @description 生产者
 */
public class Task {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            channel.basicPublish("",QUEUE_NAME,null,in.next().getBytes(StandardCharsets.UTF_8));
        }
    }
}
