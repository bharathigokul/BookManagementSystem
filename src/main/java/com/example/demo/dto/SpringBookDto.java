package com.example.demo.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

//object create 
@Component
//create table
@Entity
// create setter and getter method
@Setter
@Getter
public class SpringBookDto {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private int id;
	   private String bookName;
	   private double price;
	   private int bookCopies;
	  
	  
	   
}
