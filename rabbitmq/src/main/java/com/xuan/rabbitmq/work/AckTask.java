package com.xuan.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/3 19:33
 * @description 手动应答的生产者
 */
public class AckTask {
    public static final String ACK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(ACK_QUEUE_NAME,false,false,false,null);
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            channel.basicPublish("", ACK_QUEUE_NAME,null,in.next().getBytes(StandardCharsets.UTF_8));
        }
    }
}
