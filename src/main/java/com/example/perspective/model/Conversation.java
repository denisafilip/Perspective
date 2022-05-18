package com.example.perspective.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conversation")
public class Conversation implements Comparable<Conversation> {

    /**
     * The identifier in the database table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conversation", updatable = false, unique = true, nullable = false)
    private Integer idConversation;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_sender")
    private User sender;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_receiver")
    private User receiver;

    @NonNull
    @Column(name = "message", length = 1000, nullable = false)
    private String message;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_topic")
    private Topic topic;

    @Override
    public int compareTo(Conversation o) {
        return getIdConversation().compareTo(o.getIdConversation());
    }
}
