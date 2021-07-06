package com.xuan.rabbitmq.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/6 17:28
 * @description fanout交换机 生产者
 */
public class EmitLog {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String message = in.next();
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送" + message);
        }
    }
}
