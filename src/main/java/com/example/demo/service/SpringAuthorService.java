package com.example.demo.service;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.config.ResponseStructure;
import com.example.demo.dao.SpringAuthorDao;
import com.example.demo.dao.SpringBookDao;
import com.example.demo.dto.SpringAuthorDto;
import com.example.demo.dto.SpringBookDto;
import com.example.demo.exception.BookNotFound;

@Service
public class SpringAuthorService {

	@Autowired
	SpringAuthorDao sadao;
	@Autowired
	SpringBookDao sbdao;
	
	public ResponseEntity<ResponseStructure<SpringAuthorDto>>  saveAuthor(SpringAuthorDto dto)
	{
	        ResponseStructure<SpringAuthorDto> structure = new ResponseStructure<>();
	        structure.setStatus(HttpStatus.CREATED.value());
	        structure.setMessage("author details is save");
	        structure.setData(sadao.saveAuthor(dto));
	        
	        return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.CREATED);
	        
	}
	public ResponseEntity<ResponseStructure<SpringAuthorDto>> findAuthor(int id)
	{
		ResponseStructure<SpringAuthorDto>  structure = new ResponseStructure<>();
		SpringAuthorDto author = sadao.findAuthor(id);
		if(author != null)
		{
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("author id "+id+" is found");
			structure.setData(sadao.findAuthor(id));
			return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.FOUND);
			
		}
		else
		{
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setMessage("author id "+id+" is not found");
//			return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure, HttpStatus.NOT_FOUND);
			
			throw new BookNotFound("Book not found");
		}
	}
	public ResponseEntity<ResponseStructure<SpringAuthorDto>> updateAuthor( SpringAuthorDto dto , int id)
	{
		   ResponseStructure<SpringAuthorDto> structure = new ResponseStructure<>();
		   
		       SpringAuthorDto author = sadao.findAuthor(id);
		      
		       if(author !=null)
		       {
		    	   structure.setData(sadao.updateAuthor(dto, id));
		    	   structure.setStatus(HttpStatus.CREATED.value());
		    	   structure.setMessage("author is updated");
		    	   return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.CREATED);
		       }
		       else
		       {
		    	   structure.setStatus(HttpStatus.NOT_FOUND.value());
		    	   structure.setMessage("author id "+id+" is not available");
		    	   return new ResponseEntity<ResponseStructure<SpringAuthorDto>>(structure,HttpStatus.NOT_FOUND);
		       }
	}
	public ResponseEntity<ResponseStructure<SpringAuthorDto>> deleteAuthor(int id)
	{
		  ResponseStructure<SpringAuthorDto> structure = new ResponseStructure<>();
		   SpringAuthorDto author =    sadao.findAuthor(id);
		   if(author != null)
		   {
			   structure.setStatus(HttpStatus.CREATED.value());
			   structure.setMessage("author id "+id+" deleted");
			   structure.setData(sadao.deleteAuthor(id));
			   return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.CREATED);
		   }
		   else
		   {
			   structure.setStatus(HttpStatus.NOT_FOUND.value());
			   structure.setMessage("author id "+id+" is not present");
			   return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.NOT_FOUND);
		   }
	}
	public ResponseEntity<ResponseStructure<SpringAuthorDto>> addBookToAuthor(int aId , int bId )
	{
		ResponseStructure<SpringAuthorDto> structure = new ResponseStructure<>();
		
		SpringAuthorDto author = sadao.findAuthor(aId);
		SpringBookDto book = sbdao.findBook(bId);
		
		if(author != null)
		{
			if(book != null)
			{
			    author.setBook(book);
			    structure.setData(sadao.updateAuthor(author, aId));
			    structure.setMessage("book add to author");
			    structure.setStatus(HttpStatus.CREATED.value());
			    return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.CREATED);
			}
			else
			{
				structure.setMessage("book id "+bId+" is not found");
			    structure.setStatus(HttpStatus.NOT_FOUND.value());
			    return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			structure.setMessage("author is not found");
		    structure.setStatus(HttpStatus.NOT_FOUND.value());
		    return new ResponseEntity<ResponseStructure<SpringAuthorDto>> (structure,HttpStatus.NOT_FOUND);
		}
	}

	
	
}
