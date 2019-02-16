package com.yonyou.springboot.rabbitmq.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author: shijq
 * @Date: 2019/2/16 11:17
 */
@Component
public class CallBackSender implements  RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplatenew;

    public void send() {

        rabbitTemplatenew.setConfirmCallback(this);
        String msg="callbackSender : i am callback sender";
        System.out.println(msg );

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        System.out.println("callbackSender UUID: " + correlationData.getId());

        this.rabbitTemplatenew.convertAndSend("exchange", "topic.messages", msg, correlationData);
    }

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // TODO Auto-generated method stub
        System.out.println("callbakck confirm: " + correlationData.getId());
    }
}
