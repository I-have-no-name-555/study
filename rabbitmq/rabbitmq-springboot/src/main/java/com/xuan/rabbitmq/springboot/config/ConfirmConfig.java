package com.xuan.rabbitmq.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Xuan
 * @date :Create in 2021/7/11 16:04
 * @description 发布确认高级 配置类
 */
@Configuration
public class ConfirmConfig {
    public static final String CONFIRM_EXCHANGE = "confirm.exchange";
    public static final String CONFIRM_QUEUE = "confirm.queue";
    public static final String CONFIRM_ROUTING_KEY = "confirm.key";

    public static final String BACKUP_EXCHANGE = "backup.exchange";
    public static final String BACKUP_QUEUE = "backup.queue";
    public static final String WARNING_QUEUE = "warning.queue";

    @Bean
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE);
    }

    @Bean
    public Queue backupQueue(){
        return new Queue(BACKUP_QUEUE);
    }

    @Bean
    public Queue warningQueue(){
        return QueueBuilder.durable(WARNING_QUEUE).build();
    }

    @Bean
    public Binding backupQueueBindingBackupExchange(){
        return BindingBuilder.bind(backupQueue()).to(backupExchange());
    }
    @Bean
    public Binding warningQueueBindingBackupExchange(){
        return BindingBuilder.bind(warningQueue()).to(backupExchange());
    }


    @Bean
    public DirectExchange confirmExchange() {
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE).durable(true)
                .withArgument("alternate-exchange",BACKUP_EXCHANGE).build();
    }

    @Bean
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE).build();
    }

    @Bean
    public Binding queueBindingExchange() {
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with(CONFIRM_ROUTING_KEY);
    }
}
