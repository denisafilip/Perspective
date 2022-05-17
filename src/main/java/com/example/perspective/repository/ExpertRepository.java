package com.example.perspective.repository;

import com.example.perspective.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {

    Optional<Expert> findByEmail(String email);

    Optional<Expert> findByEmailOrUsername(String email, String username);

}
