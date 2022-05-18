package com.example.perspective.repository;

import com.example.perspective.model.Conversation;
import com.example.perspective.model.Topic;
import com.example.perspective.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    List<Conversation> findAllByReceiver(User receiver);

    List<Conversation> findAllBySender(User sender);

    List<Conversation> findAllBySenderAndReceiverAndTopic(User sender, User receiver, Topic topic);

}
