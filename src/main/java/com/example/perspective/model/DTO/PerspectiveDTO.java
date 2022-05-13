package com.example.perspective.model.DTO;

import com.example.perspective.model.Spectrum;
import com.example.perspective.model.Topic;
import com.example.perspective.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PerspectiveDTO {

    @NonNull
    private Spectrum belief;

    @NonNull
    private Topic topic;

    @NonNull
    private User user;
}
