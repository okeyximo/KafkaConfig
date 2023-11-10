package com.ximo.ximoproducer.service;

import com.ximo.ximoproducer.model.TransactionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<UUID, TransactionMessage> kafkaTemplate;

    public void send(String topicName, UUID key, TransactionMessage transactionMessage) {
        LOGGER.info("sending data='{}' to topic='{}'", transactionMessage, topicName);
        CompletableFuture<SendResult<UUID, TransactionMessage>> future = kafkaTemplate.send(topicName, key, transactionMessage);
        future.whenComplete((sendResult, exception) -> {
                    if (exception != null) {
                        LOGGER.error(exception.getMessage());
                        future.completeExceptionally(exception);
                    } else {
                        future.complete(sendResult);
                    }
                    LOGGER.info("The Id is : {} Transaction status to kafka: {}", transactionMessage.getTransactionId(), transactionMessage.getStatus());
                }
        );
    }
}
