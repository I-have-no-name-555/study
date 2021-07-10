package com.xuan.rabbitmq.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :Xuan
 * @date :Create in 2021/7/9 16:59
 * @description 延迟队列配置类
 */
@Configuration
public class TtlQueueConfig {

    public static final String NORMAL_EXCHANGE = "X";
    public static final String DEAD_EXCHANGE = "Y";
    public static final String NORMAL_QUEUE_A = "QA";
    public static final String NORMAL_QUEUE_B = "QB";
    public static final String DEAD_QUEUE = "QD";
    public static final String NORMAL_QUEUE_C = "QC";

    @Bean("normalQueueC")
    public Queue normalQueueC(){
        Map<String, Object> arguments = new HashMap<>();

        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");

        return QueueBuilder.durable(NORMAL_QUEUE_C).withArguments(arguments).build();
    }

    @Bean
    public Binding queueCBindingX(@Qualifier("normalQueueC") Queue queueC,
                                  @Qualifier("normalExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueC).to(exchange).with("XC");
    }

    @Bean("normalExchange")
    public DirectExchange normalExchange() {
        return new DirectExchange(NORMAL_EXCHANGE);
    }

    @Bean("deadExchange")
    public DirectExchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    @Bean("normalQueueA")
    public Queue normalQueueA() {
        Map<String, Object> arguments = new HashMap<>();

        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        arguments.put("x-message-ttl", 10000);

        return QueueBuilder.durable(NORMAL_QUEUE_A).withArguments(arguments).build();
    }

    @Bean("normalQueueB")
    public Queue normalQueueB() {
        Map<String, Object> arguments = new HashMap<>();

        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        arguments.put("x-message-ttl", 40000);

        return QueueBuilder.durable(NORMAL_QUEUE_B).withArguments(arguments).build();
    }

    @Bean("deadQueue")
    public Queue deadQueue() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    @Bean
    public Binding normalQueueABindingExchange(@Qualifier("normalQueueA") Queue normalQueueA,
                                               @Qualifier("normalExchange") DirectExchange normalExchange) {
        return BindingBuilder.bind(normalQueueA).to(normalExchange).with("XA");
    }

    @Bean
    public Binding normalQueueBBindingExchange(@Qualifier("normalQueueB") Queue normalQueueB,
                                               @Qualifier("normalExchange") DirectExchange normalExchange) {
        return BindingBuilder.bind(normalQueueB).to(normalExchange).with("XB");
    }

    @Bean
    public Binding deadQueueBindingExchange(@Qualifier("deadQueue") Queue deadQueue,
                                            @Qualifier("deadExchange") DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("YD");
    }

}
