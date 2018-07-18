package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ExecutiveDTO;

@Repository
public interface ExecutiveJpaRepository extends JpaRepository<ExecutiveDTO,Long> {

	public ExecutiveDTO findByUsername(String username);
}
