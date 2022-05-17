package com.example.perspective.repository;

import com.example.perspective.model.DTO.PerspectiveDTO;
import com.example.perspective.model.Perspective;
import com.example.perspective.model.Topic;
import com.example.perspective.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerspectiveRepository extends JpaRepository<Perspective, Integer> {

    List<Perspective> findAllByUser(User user);

    Optional<Perspective> findByUserAndTopic(User user, Topic topic);

}
