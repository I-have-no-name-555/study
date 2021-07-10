package com.xuan.rabbitmq.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author :Xuan
 * @date :Create in 2021/7/9 17:17
 * @description 发送信息
 */
@RestController
@Slf4j
@RequestMapping("/ttl")
public class SendMsgController {

    private final RabbitTemplate rabbitTemplate;

    public SendMsgController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message) {
        log.info("当前时间：{}，发送一条信息给两个TTL队列:{}", new Date(), message);

        rabbitTemplate.convertAndSend("X", "XA", "消息来自ttl为10s的队列：" + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自ttl为40s的队列：" + message);
    }

    @GetMapping("/sedExpiration/{message}/{ttl}")
    public void sendMsg(@PathVariable String message, @PathVariable String ttl) {
        log.info("当前时间：{}，发送一条时长{}毫秒信息给TTL队列QC:{}", new Date(), ttl, message);

        rabbitTemplate.convertAndSend("X", "XC", message,
                msg -> {
                    msg.getMessageProperties().setExpiration(ttl);
                    return msg;
                });
    }


}
