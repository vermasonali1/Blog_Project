package com.myblogproject.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myblogproject.entities.Category;
import com.myblogproject.entities.Post;
public interface PostRepo extends JpaRepository<Post,Integer> {
 
	
	
//	custom finder method------------
//	iska use sari post ko nikalne ke liye krte h
	List<Post>  findByUser(com.myblogproject.entities.User user);
	List<Post>  findByCategory(Category category);
	
//	searchin ke time exception show nahi ho isliye iss query k use kiya h
	
//	@Query("select p from post p where p.title like:key")
	List<Post> findByTitleContaining(@Param("key") String title);

}
