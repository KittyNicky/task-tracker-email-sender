package com.kittynicky.tasktrackermail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    private String email;
    private String subject;
    private String text;
}