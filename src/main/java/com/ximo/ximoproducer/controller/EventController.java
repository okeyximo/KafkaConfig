package com.ximo.ximoproducer.controller;

import com.ximo.ximoproducer.model.TransactionMessage;
import com.ximo.ximoproducer.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class EventController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/event")
    ResponseEntity<String> postEvent(@RequestBody TransactionMessage transactionMessage) {
        UUID uuid = UUID.randomUUID();
        log.info("we received the transaction with the key {}", uuid);
        kafkaProducerService.send("transaction-topic", uuid, transactionMessage);
        return ResponseEntity.ok("Event sent");
    }


}
