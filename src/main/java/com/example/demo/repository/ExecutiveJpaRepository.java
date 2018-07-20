package com.example.demo.repository;

import com.example.demo.dto.ExecutiveDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveJpaRepository extends JpaRepository<ExecutiveDTO, Long> {
    public ExecutiveDTO findByUsername(String username);
}
