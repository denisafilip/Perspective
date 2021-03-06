package com.example.perspective.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "resource")
public class Resource {

    /**
     * The identifier in the database table.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_resource", updatable = false, unique = true, nullable = false)
    private Integer idResource;

    @NonNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NonNull
    @Column(name = "mimeType", nullable = false)
    private String mimeType;

    @NonNull
    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_topic")
    private Topic topic;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_expert")
    private Expert expert;

}
