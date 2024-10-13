package com.kittynicky.tasktrackermail.kafka;

import com.kittynicky.tasktrackermail.dto.MailRequest;
import com.kittynicky.tasktrackermail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final MailService mailService;

    @KafkaListener(
            containerFactory = "kafkaListenerContainerFactory",
            topics = "EMAIL_SENDING_TASKS",
            groupId = "1")
    public void listen(ConsumerRecord<String, MailRequest> consumerRecord) {
        var request = consumerRecord.value();
        mailService.sendConfirmationEmail(request);
        log.info("Message sent");
    }
}
