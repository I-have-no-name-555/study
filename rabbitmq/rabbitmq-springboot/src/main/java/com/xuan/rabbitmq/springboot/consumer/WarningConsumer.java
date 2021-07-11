package com.xuan.rabbitmq.springboot.consumer;

import com.xuan.rabbitmq.springboot.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author :Xuan
 * @date :Create in 2021/7/11 17:55
 * @description 备份交换机 报警
 */
@Component
@Slf4j
public class WarningConsumer {
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE)
    public void receiveWarningMessage(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.warn("发现不可路由消息：{}",msg);
    }
}
