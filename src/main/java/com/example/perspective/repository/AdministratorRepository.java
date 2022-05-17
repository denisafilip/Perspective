package com.example.perspective.repository;

import com.example.perspective.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    Optional<Administrator> findByEmail(String email);

    List<Administrator> findAll();
}
