package com.kittynicky.tasktrackermail.service;

import com.kittynicky.tasktrackermail.dto.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;


    public void sendConfirmationEmail(MailRequest request) {
        try {
            log.info("Sending confirmation email for request: {}", request);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessage.setContent(request.getText(), "text/html; charset=utf-8");
            mimeMessageHelper.setTo(request.getEmail());
            mimeMessageHelper.setSubject(request.getSubject());
            mimeMessageHelper.setText(request.getText(), true);

            mailSender.send(mimeMessage);

            log.info("Successfully sent confirmation email for request: {}", request);
        } catch (MessagingException e) {
            log.error("Failed to send confirmation email for request: {}", request, e);
            throw new RuntimeException(e);
        }
    }
}
