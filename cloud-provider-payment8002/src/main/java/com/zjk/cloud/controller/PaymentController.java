package com.zjk.cloud.controller;

import com.zjk.cloud.entities.CommonResult;
import com.zjk.cloud.entities.Payment;
import com.zjk.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("********插入结果："+i);

        if (i >0){
            return new CommonResult(200,"插入数据成功,serverPort"+serverPort,i);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("********查询结果："+paymentById);
        if (paymentById!=null){
            return new CommonResult(200,"查询数据成功serverPort"+serverPort,paymentById);
        }else {
            return new CommonResult(444,"查询数据失败",null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi paymentZipkin";
    }
}
