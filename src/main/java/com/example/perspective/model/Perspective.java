package com.example.perspective.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perspective")
public class Perspective {

    /**
     * The identifier in the database table.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_perspective", updatable = false, unique = true, nullable = false)
    private Integer idPerspective;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "belief", nullable = false)
    private Spectrum belief;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    private User user;
}
