package com.zjk.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.OverridesAttribute;

@Component
public class PaymentServiceHystrixImpl implements PaymentServiceHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        System.out.println("Fallback:paymentInfo_ok");
        return null;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_timeout(Integer id) {
        System.out.println("Fallback:paymentInfo_timeout");
        return "Fallback : paymentInfo_timeout";
    }
}
