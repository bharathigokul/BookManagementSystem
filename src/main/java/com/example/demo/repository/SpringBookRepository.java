package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.SpringBookDto;


// repository persent in all method in save(),delete(),findall(),findid() 
//repository present in all method so create one interface

                                                          //classname   primaryKey
public interface SpringBookRepository extends JpaRepository<SpringBookDto, Integer>
{
	// custom query   
	
      @Query("select b from SpringBookDto b where b.bookCopies>?1")
	  public List<SpringBookDto> lessThan(int copies);
}

