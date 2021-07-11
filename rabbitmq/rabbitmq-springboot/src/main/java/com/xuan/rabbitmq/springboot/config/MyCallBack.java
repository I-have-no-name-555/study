package com.xuan.rabbitmq.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/**
 * @author :Xuan
 * @date :Create in 2021/7/11 16:23
 * @description ConfirmCallBack的实现类
 */
@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    private final RabbitTemplate rabbitTemplate;

    public MyCallBack(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData == null ? "null" : correlationData.getId();
        if (ack) {
            log.info("交换机接受到ID为{}的消息", id);
        } else {
            log.info("交换机未接受到ID为 {} 的消息，原因为：{}", id, cause);

        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("消息：{} 被交换机 {} 退回，原因为：{}，路由key：{}",
                new String(returned.getMessage().getBody(), StandardCharsets.UTF_8),
                returned.getExchange(), returned.getReplyText(), returned.getRoutingKey());
    }
}
