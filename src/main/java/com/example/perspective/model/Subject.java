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
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private List<Expert> experts;

    @JsonIgnore
    @OneToMany(mappedBy = "subject", orphanRemoval = true)
    private List<Topic> topics;
}
