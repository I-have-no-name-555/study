package com.xuan.rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.xuan.rabbitmq.util.RabbitMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * @author :Xuan
 * @date :Create in 2021/7/8 16:44
 * @description topic交换机 生产者
 */
public class EmitLog {
    public static final String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        Map<String,String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("quick.orange.rabbit","被Q1Q2接收");
        bindingKeyMap.put("quick.orange.male.rabbit","被丢弃");
        bindingKeyMap.put("lazy.orange.male.rabbit","被Q2接收");
        bindingKeyMap.put("lazy.orange.elephant","被Q1Q2接收");
        bindingKeyMap.put("quick.orange.fox","被Q1接收");
        bindingKeyMap.put("quick.brown.fox","被丢弃");
        bindingKeyMap.put("lazy.brown.fox","被Q2接收");
        bindingKeyMap.put("lazy.pink.rabbit","被Q2接收");

        Set<Map.Entry<String, String>> entrySet = bindingKeyMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            channel.basicPublish(EXCHANGE_NAME,entry.getKey(),null,entry.getValue().getBytes(StandardCharsets.UTF_8));
        }

    }
}
