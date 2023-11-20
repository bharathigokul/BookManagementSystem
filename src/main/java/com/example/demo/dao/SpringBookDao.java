package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.SpringBookDto;
import com.example.demo.repository.SpringBookRepository;

@Repository
public class SpringBookDao 
{
	     @Autowired
         SpringBookRepository repo;
	
	    public SpringBookDto saveBook(SpringBookDto sb)
	    {
	    	return repo.save(sb);
	    }
	    public SpringBookDto findBook(int id)
	    {
	    	Optional<SpringBookDto> op = repo.findById(id);
	    	if(op.isPresent())
	    	{
	    		return op.get();
	    	}
	    	return null;
	    }
	    public SpringBookDto deleteBook(int id)
	    {
	    	SpringBookDto sbdto = findBook(id);
	    	if(sbdto != null)
	    	{
	    		 repo.delete(sbdto);
	    		 return sbdto;
	    	}
	    	return null;
	    	
	    }
	    public SpringBookDto updateBook(SpringBookDto sb , int id)
	    {
	            SpringBookDto exBook = findBook(id);
	            if(exBook!=null)
	            {

	            	
	            	if(sb.getBookName()==null)
	            	{ 
	            		sb.setBookName(exBook.getBookName());
	            	}
	            	if(sb.getBookCopies()<=0)
	            	{
	            		sb.setBookCopies(exBook.getBookCopies());
	            	}
	            	if(sb.getPrice()<=0)
	            	{
	            		sb.setPrice(exBook.getPrice());
	            	}
	            	
	                  sb.setId(id);
	                  return repo.save(sb);
	            	
	            }
	            return null;
	    }
	    public List<SpringBookDto> getAllBook()
	    {
	    	   return   repo.findAll();
	    }
	    public List<SpringBookDto> getCopies(int copies)
	    {
	    	return repo.lessThan(copies);
	    }
	    
	 
}
