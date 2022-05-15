package com.example.perspective.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "expert")
public class Expert extends Account {

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "expert_subject",
            joinColumns = @JoinColumn(name = "id_expert"),
            inverseJoinColumns = @JoinColumn(name = "id_subject"))
    private List<Subject> subjects;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "expert", orphanRemoval = true)
    private List<Resource> resources;

    /**
     * Constructor used for creating an expert instance.
     * @param username of the expert
     * @param firstName of the expert
     * @param lastName of the expert
     * @param email of the expert
     * @param password of the expert
     */
    @Builder(builderMethodName = "ExpertBuilder")
    public Expert(@NonNull String username,
                  @NonNull String firstName,
                  @NonNull String lastName,
                  @NonNull String email,
                  @NonNull String password,
                  @NonNull List<Subject> subjects) {
        super(username, firstName, lastName, email, password);
        this.subjects = subjects;
    }
}
