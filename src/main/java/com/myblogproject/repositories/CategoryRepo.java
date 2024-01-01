package com.myblogproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblogproject.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>  {

}
