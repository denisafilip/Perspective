package com.example.perspective.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    /**
     * The identifier in the database table.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_topic", updatable = false, unique = true, nullable = false)
    private Integer idTopic;

    @NonNull
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "topics")
    private List<Expert> experts;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private List<Subject> subjects;
}
