package com.blog.Services.Implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Modules.Category;
import com.blog.Modules.Post;
import com.blog.Modules.User;
import com.blog.Payloads.PostDto;
import com.blog.Payloads.PostResponse;
import com.blog.Repositories.CategoryRepo;
import com.blog.Repositories.PostRepo;
import com.blog.Repositories.UserRepo;
import com.blog.Services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer cateId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = this.categoryRepo.findById(cateId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", cateId));
		Post post =	this.modelMapper.map(postDto, Post.class);
		post.setImage("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savePost = this.postRepo.save(post);
		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
			Post post1 =	this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", id));
			
		post1.setTitle(postDto.getTitle());
		post1.setContent(postDto.getContent());
		post1.setImage(postDto.getImage());
		post1.setAddedDate(postDto.getAddedDate());
		Post savePost =	this.postRepo.save(post1);
		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
	Post post =	this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
			
			this.postRepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy,String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		
		
		Page<Post> post = this.postRepo.findAll(page);
		
		List<Post> content = post.getContent();
		
		
		
		List<PostDto> liPostDto = content.stream().map( (p) -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(liPostDto);
		postResponse.setPageNumber(post.getNumber());
		postResponse.setPageSize(post.getSize());
		postResponse.setTotalElement(post.getTotalElements());
		postResponse.setTotalPages(post.getTotalPages());
		postResponse.setLastPage(post.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer id) {

		Post post =	this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer cateId) {
		
		Category cat = this.categoryRepo.findById(cateId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", cateId));
		
		List<Post> post = this.postRepo.findByCategory(cat);
		
			List<PostDto> catPost	=	post.stream().map( (p) -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		
		
		
		return catPost;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> userPost = posts.stream().map((p) -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		return userPost;
	}
//--------------------------------------------------- Searching ----------------------------------------------------------------------------------yu
	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> titleContaining = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> posts = titleContaining.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return posts;
	}

}
