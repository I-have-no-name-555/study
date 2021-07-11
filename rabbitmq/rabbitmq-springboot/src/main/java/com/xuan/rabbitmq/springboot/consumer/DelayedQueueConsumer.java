package com.xuan.rabbitmq.springboot.consumer;

import com.xuan.rabbitmq.springboot.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author :Xuan
 * @date :Create in 2021/7/11 11:12
 * @description 基于插件延迟队列 消费者
 */
@Component
@Slf4j
public class DelayedQueueConsumer {
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE)
    public void receiveDelayQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("当前时间：{}，收到延迟队列消息：{}",new Date(),msg);
    }

}
