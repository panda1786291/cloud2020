package com.zjk.cloud.service.impl;

import com.zjk.cloud.service.IMessageProvider;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@EnableBinding(Source.class) //定义消息推送管道
public class MessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String s = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(s).build());
        System.out.println("serial"+s);
        return null;
    }
}
