package com.example.perspective.model.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ConversationDTO {

    @NonNull
    private UserDTO sender;

    @NonNull
    private UserDTO receiver;

    @NonNull
    private TopicDTO topicDTO;

    @NonNull
    private String message;
}
