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
import com.example.demo.dto.SpringAuthorDto;
import com.example.demo.dto.SpringBookDto;
import com.example.demo.service.SpringAuthorService;

@RestController
@RequestMapping("/author")
public class SpringAuthorController {

	@Autowired
	SpringAuthorService service;
	
	
	 
	 @PostMapping
	 public ResponseEntity<ResponseStructure<SpringAuthorDto>> saveBook(@RequestBody SpringAuthorDto dto)
	 {
		return service.saveAuthor(dto);
	 }
	 @GetMapping
    public ResponseEntity<ResponseStructure<SpringAuthorDto>> findBook(@RequestParam int id)
    {
   	 return service.findAuthor(id);
    }
	 @PutMapping
	 public ResponseEntity<ResponseStructure<SpringAuthorDto>> updateAuthor(@RequestBody SpringAuthorDto author , @RequestParam int id)
	 {
		 return service.updateAuthor(author, id);
	 }
	 @DeleteMapping
	 public ResponseEntity<ResponseStructure<SpringAuthorDto>> deleteAuthor( @RequestParam int id)
	 {
		 return service.deleteAuthor(id);
	 }
	 @PutMapping("/addBookToAuthor")
	 public ResponseEntity<ResponseStructure<SpringAuthorDto>> addBookToAuthor(@RequestParam int aId , @RequestParam int bId)
	 {
		 return service.addBookToAuthor(aId, bId);
	 } 
	
}
