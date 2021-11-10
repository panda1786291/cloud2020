package com.zjk.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zjk.cloud.entities.CommonResult;
import com.zjk.cloud.entities.Payment;
import com.zjk.cloud.handler.CustomerBlockerHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResult(){
        return new CommonResult(200,"按照资源名称限流ok",new Payment(200L,"serial01"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t服务器不可用");
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockerHandler.class,
            blockHandler = "handlerException1"
    )
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按照用户自定义", new Payment(2020L,"serial03"));
    }
}
