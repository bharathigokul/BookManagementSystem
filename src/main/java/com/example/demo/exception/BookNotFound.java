package com.example.demo.exception;

import org.springframework.stereotype.Controller;

import lombok.Getter;


@Getter
public class BookNotFound extends RuntimeException
{
     private String message;
     
     public BookNotFound(String message)
     {
    	 this.message = message;
     }
}
