package com.blog.Repositories;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Modules.Category;
import com.blog.Modules.Post;
import com.blog.Modules.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
