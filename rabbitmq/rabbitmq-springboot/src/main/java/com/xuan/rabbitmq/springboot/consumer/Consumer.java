package com.xuan.rabbitmq.springboot.consumer;

import com.xuan.rabbitmq.springboot.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author :Xuan
 * @date :Create in 2021/7/11 16:14
 * @description 发布确认高级 消费者
 */
@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE)
    public void receiveConfirmMessage(Message message) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("接收信息：{}", msg);
    }
}
