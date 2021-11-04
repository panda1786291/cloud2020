package com.zjk.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zjk.cloud.service.PaymentServiceHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "paymentTimeOutFallBackMethod",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
})
public class OrderHystrixController {
    @Autowired
    private PaymentServiceHystrixService paymentServiceHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String s = paymentServiceHystrixService.paymentInfo_ok(id);
        System.out.println("result:"+s);
        return s;
    }


//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
//    })
    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        String s = paymentServiceHystrixService.paymentInfo_timeout(id);
        System.out.println("result:"+s);
        return s;
    }

    public String paymentTimeOutFallBackMethod(){
        System.out.println("我是消费者80 请稍后再试");
        return "我是消费者80 请稍后再试";
    }

}
