package com.zjk.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zjk.cloud.entities.CommonResult;
import com.zjk.cloud.entities.Payment;
import org.springframework.web.bind.annotation.RestController;


public class CustomerBlockerHandler {
    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(444,"按照客户自定义：Global handlerException1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"按照客户自定义：Global handlerException2");
    }
}
