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
    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @NonNull
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private List<Perspective> perspectives;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private List<Resource> resources;
}
