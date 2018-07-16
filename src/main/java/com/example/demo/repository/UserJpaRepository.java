package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.UserDTO;


@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO,Long>{

UserDTO findByName(String name);
}
