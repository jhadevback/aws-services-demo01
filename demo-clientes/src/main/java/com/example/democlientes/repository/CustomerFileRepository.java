package com.example.democlientes.repository;

import com.example.democlientes.entity.CustomerFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFileRepository extends JpaRepository<CustomerFiles, Long> {
}
