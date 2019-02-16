package com.yonyou.springboot.rabbitmq.controller;

import com.yonyou.springboot.rabbitmq.callback.CallBackSender;
import com.yonyou.springboot.rabbitmq.fanout.FanoutSender;
import com.yonyou.springboot.rabbitmq.service.oneToOne.HelloSender;
import com.yonyou.springboot.rabbitmq.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Author: shijq
 * @Date: 2019/2/14 14:16
 */
@RestController
public class RabbitOneToOneTest {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private CallBackSender callBackSender;

    @PostMapping("/hello")
    public Object hello(){
        while(2>1){
            helloSender.send();
            int i = new Random().nextInt(10000);
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * topic exchange类型rabbitmq测试
     */
    @PostMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    /**
     * fanout exchange类型rabbitmq测试
     */
    @PostMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }

    @PostMapping("/callback")
    public void callbak() {
        callBackSender.send();
    }
}
