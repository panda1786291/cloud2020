package com.zjk.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池"+Thread.currentThread().getName()+"\tpayment_ok,id"+id+"haha";
    }

    /**
     * fallback方法，fallback指向paymentInfo_timeout_handler方法，当不符合规定或者出错的时候，就调用该方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "10000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try {

            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"\tpayment_TimeOut,id"+id+"超时";
    }


    public String paymentInfo_timeout_handler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"\tpayment_TimeOut_handler,id"+id+"handler";

    }


    //-----服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(Long id){
        if (id<0){
            throw new RuntimeException("****id 不能为负数");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+s;
    }

    public String paymentCircuitBreaker_Fallback(Long id){
        return "id 不能为负数";
    }

}
