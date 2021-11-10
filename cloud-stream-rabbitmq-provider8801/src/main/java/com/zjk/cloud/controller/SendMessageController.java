package com.zjk.cloud.controller;

import com.zjk.cloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    private IMessageProvider messageProvider;

    @GetMapping("/sendMsg")
    public String sendMessage(){
        return messageProvider.send();
    }
}
