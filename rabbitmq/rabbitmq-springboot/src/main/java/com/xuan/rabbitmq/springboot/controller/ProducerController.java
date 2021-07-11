package com.xuan.rabbitmq.springboot.controller;

import com.xuan.rabbitmq.springboot.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Xuan
 * @date :Create in 2021/7/11 16:10
 * @description 发布确认高级 生产者
 */
@RestController
@RequestMapping("/confirm")
@Slf4j
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message) {

        CorrelationData correlationData = new CorrelationData();

        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,
                ConfirmConfig.CONFIRM_ROUTING_KEY, message, correlationData);
        log.info("发送消息内容：{}", message);
    }

    //测试备份交换机，向错误路由发送消息
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable String message) {

        CorrelationData correlationData = new CorrelationData();

        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,
                ConfirmConfig.CONFIRM_ROUTING_KEY + 2, message, correlationData);
        log.info("发送消息内容：{}", message);
    }
}
