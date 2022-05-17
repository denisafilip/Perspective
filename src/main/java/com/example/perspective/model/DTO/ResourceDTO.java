package com.example.perspective.model.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ResourceDTO {

    private String name;

    private MultipartFile file;

    private String mimeType;

    private byte[] data;

    @NonNull
    private TopicDTO topicDTO;

    @NonNull
    private ExpertDTO expertDTO;
}
