package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Investment;
@Repository
public interface InvestmentRepo extends JpaRepository<Investment,Long>{

    List<Investment> findByType(String type);

    List<Investment> findByStatus(String status);

    Optional<Investment> findByName(String name);

    

}
