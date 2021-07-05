package com.xuan.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/4 18:34
 * @description 发布确认 生产者
 */
public class ConfirmMessage {

    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        ConfirmMessage.publishMessageIndividually();
        ConfirmMessage.publishMessageBatch();
        ConfirmMessage.publishMessageAsync();
    }

    public static void publishMessageIndividually() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtil.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        channel.confirmSelect();

        long start = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            channel.waitForConfirms();
        }
        long end = System.currentTimeMillis();
        System.out.println("单个确认:" + (end - start));
        channel.close();
    }

    public static void publishMessageBatch() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtil.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        channel.confirmSelect();
        int batchSize = 100;
        long start = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            if (i % batchSize == 0){
                channel.waitForConfirms();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批量确认:" + (end - start));
        channel.close();
    }

    public static void publishMessageAsync() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtil.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        channel.confirmSelect();
        ConcurrentSkipListMap<Long,String> outstandingConfirms = new ConcurrentSkipListMap<>();
        long start = System.currentTimeMillis();
        channel.addConfirmListener((deliveryTag, multiple) -> {
            if (multiple){
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            }else {
                outstandingConfirms.remove(deliveryTag);
            }
        },(deliveryTag, multiple) -> System.out.println("确认失败" + deliveryTag));
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }
        long end = System.currentTimeMillis();
        System.out.println("异步确认:" + (end - start));
        channel.close();
    }
}
