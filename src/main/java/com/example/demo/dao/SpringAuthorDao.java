package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.SpringAuthorDto;
import com.example.demo.repository.SpringAuthorRepository;

@Repository
public class SpringAuthorDao {
	
	 @Autowired
	 SpringAuthorRepository repo;
	
	public SpringAuthorDto saveAuthor(SpringAuthorDto dto)
	{
		 return repo.save(dto);
	}
	public SpringAuthorDto findAuthor(int id)
	{
		Optional<SpringAuthorDto> op = repo.findById(id);
		if(op.isPresent())
		{
			return op.get();
		}
		return null;
	}
	public SpringAuthorDto updateAuthor(SpringAuthorDto dto , int id)
	{
		SpringAuthorDto author = findAuthor(id);
		
		if(author != null)
		{
			if(dto.getAuthorName()==null)
			{
				dto.setAuthorName(author.getAuthorName());
			}
			if(dto.getAuthorLocation()==null)
			{
				dto.setAuthorLocation(author.getAuthorLocation());
			}
			
			author.setId(id);
			return repo.save(dto);
		}
		return null;
		
	}
	public SpringAuthorDto deleteAuthor(int id)
	{
		SpringAuthorDto author = findAuthor(id);
		if(author != null)
		{
			repo.delete(author);
			return author;
		}
		return null;
	}
	
	

}
