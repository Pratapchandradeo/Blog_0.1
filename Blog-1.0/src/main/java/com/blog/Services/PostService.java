package com.blog.Services;

import java.util.List;
import com.blog.Payloads.PostDto;
import com.blog.Payloads.PostResponse;

public interface PostService {
	
	
//create post
	PostDto createPost(PostDto postDto,Integer userId, Integer cateId);
//	update post
	PostDto updatePost(PostDto postDto, Integer id);
	
//	delete post
	void deletePost(Integer id);
	
//	get all post
	PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy,String sortDir);
//	get by id
	PostDto getPostById(Integer id);
	
//	get post by category
	
	List<PostDto> getPostByCategory(Integer cateId);
	
//	get post by user
	
	List<PostDto> getPostByUser(Integer userId);
	
//	search by keyword
	List<PostDto> searchPost(String keyword);
}
