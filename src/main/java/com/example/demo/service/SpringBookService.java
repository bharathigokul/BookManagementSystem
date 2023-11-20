package com.example.demo.service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.config.ResponseStructure;
import com.example.demo.dao.SpringBookDao;
import com.example.demo.dto.SpringBookDto;



@Service
public class SpringBookService
{
	@Autowired
     SpringBookDao sbdao;
	
	
//	public SpringBookDto saveBook(SpringBookDto sbdto)
//	{
//		return sbdao.saveBook(sbdto);
//	}
	
	public ResponseEntity<ResponseStructure<SpringBookDto>> saveBook(SpringBookDto sbdto)
	{
	       ResponseStructure<SpringBookDto> structure = new ResponseStructure<>(); 
	       structure.setStatus(HttpStatus.CREATED.value());
	       structure.setMessage("your data is saved");
	       structure.setData(sbdao.saveBook(sbdto));
	       
	       return new ResponseEntity<ResponseStructure<SpringBookDto>> (structure,HttpStatus.CREATED) ;
	
	}
	
	public ResponseEntity<ResponseStructure<SpringBookDto>>  findBook( int id)
	{
		ResponseStructure<SpringBookDto> structure = new ResponseStructure<>();
		 SpringBookDto book = sbdao.findBook(id);
		 if(book !=null)
		 {
			 structure.setStatus(HttpStatus.FOUND.value());
			 structure.setMessage("book id "+id+ " is found");
			 structure.setData(sbdao.findBook(id));
			 
			 return new ResponseEntity<ResponseStructure<SpringBookDto>> (structure,HttpStatus.FOUND);
		 }
		 else
		 {
			 structure.setStatus(HttpStatus.NOT_FOUND.value());
			 structure.setMessage("book id " + id +" is not found");
			 
			 return new ResponseEntity<ResponseStructure<SpringBookDto>> (structure,HttpStatus.NOT_FOUND);
		 }		
		
	}
	public ResponseEntity<ResponseStructure<SpringBookDto>> updateBook( SpringBookDto dto , int id)
	{
		   ResponseStructure<SpringBookDto> structure = new ResponseStructure<>();
		   
		       SpringBookDto book = sbdao.findBook(id);
		      
		       if(book !=null)
		       {
		    	   structure.setData(sbdao.updateBook(dto, id));
		    	   structure.setStatus(HttpStatus.CREATED.value());
		    	   structure.setMessage("book is updated");
		    	   return new ResponseEntity<ResponseStructure<SpringBookDto>> (structure,HttpStatus.CREATED);
		       }
		       else
		       {
		    	   structure.setStatus(HttpStatus.NOT_FOUND.value());
		    	   structure.setMessage("book id "+id+" is not available");
		    	   return new ResponseEntity<ResponseStructure<SpringBookDto>>(structure,HttpStatus.NOT_FOUND);
		       }
	}
	public ResponseEntity<ResponseStructure<SpringBookDto>> deleteBook(int id)
	{
		  ResponseStructure<SpringBookDto> structure = new ResponseStructure<>();
		   SpringBookDto exBook =    sbdao.findBook(id);
		   if(exBook != null)
		   {
			   structure.setStatus(HttpStatus.CREATED.value());
			   structure.setMessage("book id "+id+" deleted");
			   structure.setData(sbdao.deleteBook(id));
			   return new ResponseEntity<ResponseStructure<SpringBookDto>> (structure,HttpStatus.CREATED);
		   }
		   else
		   {
			   structure.setStatus(HttpStatus.NOT_FOUND.value());
			   structure.setMessage("book id "+id+" is not present");
			   return new ResponseEntity<ResponseStructure<SpringBookDto>> (structure,HttpStatus.NOT_FOUND);
		   }
	}

	public ResponseEntity<ResponseStructure<List<SpringBookDto>>> findAllBook()
	{
		ResponseStructure<List<SpringBookDto>> structure = new ResponseStructure<>();
		
		List<SpringBookDto> allBook = sbdao.getAllBook();
		
		if( !allBook.isEmpty() )
		{
		
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("all book details");
			structure.setData(sbdao.getAllBook());
			return new ResponseEntity<ResponseStructure<List<SpringBookDto>>> (structure,HttpStatus.FOUND);
			
		}
		else
		{
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage("No record");
			return new ResponseEntity<ResponseStructure<List<SpringBookDto>>> (structure,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<List<SpringBookDto>>> getCopies(int copies)
	{
		ResponseStructure<List<SpringBookDto>> structure = new ResponseStructure<>();
		
		 if(!sbdao.getCopies(copies).isEmpty())
		 {
			    structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("all book copies");
				structure.setData(sbdao.getCopies(copies));
				return new ResponseEntity<ResponseStructure<List<SpringBookDto>>>(structure,HttpStatus.FOUND);
		 }
		 else
		 {
			    structure.setStatus(HttpStatus.NOT_FOUND.value());
				structure.setMessage(" book copies not found");
				return new ResponseEntity<ResponseStructure<List<SpringBookDto>>>(structure,HttpStatus.NOT_FOUND);
		 }
		
	}
	
}

