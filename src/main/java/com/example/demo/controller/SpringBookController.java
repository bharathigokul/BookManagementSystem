package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ResponseStructure;
import com.example.demo.dto.SpringBookDto;
import com.example.demo.service.SpringBookService;

@RestController
@RequestMapping("/book")
public class SpringBookController { 
	
	 @Autowired 
	 SpringBookService service;
	 
	 
	 @PostMapping
	 public ResponseEntity<ResponseStructure<SpringBookDto>> saveBook(@RequestBody SpringBookDto sbdto)
	 {
		return service.saveBook(sbdto);
	 }
	 @GetMapping
     public ResponseEntity<ResponseStructure<SpringBookDto>> findBook(@RequestParam int id)
     {
    	 return service.findBook(id);
     }
	 @PutMapping
	 public ResponseEntity<ResponseStructure<SpringBookDto>> updateBook(@RequestBody SpringBookDto book , @RequestParam int id)
	 {
		 return service.updateBook(book, id);
	 }
	 @DeleteMapping
	 public ResponseEntity<ResponseStructure<SpringBookDto>> deleteBook( @RequestParam int id)
	 {
		 return service.deleteBook(id);
	 }
	 @GetMapping("/all")
	 public ResponseEntity<ResponseStructure<List<SpringBookDto>>> findAllBook()
	 {
		 return service.findAllBook();
	 }
	 @GetMapping("/allBookCopies")
	 public ResponseEntity<ResponseStructure<List<SpringBookDto>>> getCopies(int copies)
	 {
		 return service.getCopies(copies);
	 }
}
