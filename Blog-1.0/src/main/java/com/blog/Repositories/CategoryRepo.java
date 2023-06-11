package com.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Modules.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
