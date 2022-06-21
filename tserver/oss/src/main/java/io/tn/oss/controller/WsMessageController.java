package io.tn.oss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("")
public class WsMessageController {

    @Autowired
    SimpMessagingTemplate template;

    @Async
    @GetMapping("/sub")
    void sub() throws InterruptedException {
        log.info("http 方式触发订阅");
        for (int i = 0; i < 10; i++) {
            // 可以灵活设置成通道地址，实现发布订阅的功能
            var channel = "/topic/subscribeTopic";
            Thread.sleep(5 * 1000);
            template.convertAndSend(channel, Instant.now());
        }
    }

    @SubscribeMapping("/subscribeTopic")
    long subs() {
        log.info("订阅日志 = @SubscribeMapping(\"/subscribeTopic\")");
        return Instant.now().toEpochMilli();
    }

    @SubscribeMapping("/broadcast")
    String subscribe(Message<String> message) {
        log.info("向客户端广播消息 = {}",message.getPayload());
        return message.getPayload();
    }

    @MessageMapping("/sendToServer")
    void handleMessage(String message) {
        log.info("服务器接受到消息 = {}", message);
    }

    @MessageMapping("/sendToTopic")
    @SendTo("/topic/broadcast")
    String sendToTopic(Message<String> message) {
        log.info("message = {}", message);
        return message.getPayload();
    }
}
