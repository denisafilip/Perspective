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
@Table(name = "subject")
public class Subject {

    /**
     * The identifier in the database table.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_subject", updatable = false, unique = true, nullable = false)
    private Integer idSubject;

    @NonNull
    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @NonNull
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_topic")
    private Topic topic;

    @JsonIgnore
    @OneToMany(mappedBy = "subject", orphanRemoval = true)
    private List<Perspective> perspectives;

    @JsonIgnore
    @OneToMany(mappedBy = "subject", orphanRemoval = true)
    private List<Resource> resources;
}
