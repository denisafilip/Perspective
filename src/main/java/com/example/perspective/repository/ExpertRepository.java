package com.example.perspective.repository;

import com.example.perspective.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {

    Optional<Expert> findByEmail(String email);

}
