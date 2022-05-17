package com.example.perspective.repository;

import com.example.perspective.model.Expert;
import com.example.perspective.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    List<Resource> findAllByExpert(Expert expert);

    Optional<Resource> findByName(String name);
}
