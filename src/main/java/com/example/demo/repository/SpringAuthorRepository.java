package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.SpringAuthorDto;



public interface SpringAuthorRepository extends JpaRepository<SpringAuthorDto, Integer>
{

}
