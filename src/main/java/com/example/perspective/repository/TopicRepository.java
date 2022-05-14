package com.example.perspective.repository;

import com.example.perspective.model.Subject;
import com.example.perspective.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Optional<Topic> findByName(String name);

    List<Topic> findAllBySubject(Subject subject);

}
