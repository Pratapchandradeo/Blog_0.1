package com.blog.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.Config.AppConstants;
import com.blog.Payloads.ApiResponse;
import com.blog.Payloads.PostDto;
import com.blog.Payloads.PostResponse;
import com.blog.Services.FileService;
import com.blog.Services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	
	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto post = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
		
	}
	
	//get by user also contain pagination 
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByuser(@PathVariable("userId") Integer userId
			
			){
		
		List<PostDto> posts =  this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	
	// get By Category 
	
	@GetMapping("category/{cateId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory( @PathVariable Integer cateId){
		
		List<PostDto> posts = this.postService.getPostByCategory(cateId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
//	get all post
//---------------------------------------------------------------- pagination -----------------------------------------------------------------------------// 
	@GetMapping("/posts")
	 public ResponseEntity<PostResponse> getAllPost(
			 @RequestParam(value = "pageNo",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNo,
			 @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			 @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			 @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
			 ){
			PostResponse posts =	this.postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
		 
	 }
	
//	Get Post By ID
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
		PostDto post =this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		
	}
	
//	Delete A Post By Id
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Integer postId){
		
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully !!", true), HttpStatus.OK);
		
	}
	
//	Update Post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post, @PathVariable("postId") Integer postId){
			PostDto postRes =	this.postService.updatePost(post, postId);
			
			return new ResponseEntity<PostDto>(postRes,HttpStatus.OK);
		
	}

	
//	--------------------------------------------------------------- Searching -------------------------------------------------------------------------------//
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle(
			@PathVariable("keywords") String keywords
			){
		List<PostDto> searchPost = this.postService.searchPost(keywords);
		
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
		
	}
	
//	------------------------------------------post Image Upload --------------------------------------------
	
	
	@PostMapping("/posts/image/upload/{postID}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postID
			) throws IOException{
		PostDto postDto = this.postService.getPostById(postID);
		
		String fileName = this.fileService.uploadImage(path, image);
		
		
		postDto.setImage(fileName);
		
		PostDto updatePost = this.postService.updatePost(postDto, postID);
		
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
//	------------------------------------Method to serve Image --------------------------------------------------------------------------
	
	@GetMapping(value = "/posts/image/download/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			) throws IOException {
		InputStream resourse = this.fileService.getResource(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourse, response.getOutputStream());
		
		
	}
	
	
	
	
	
	
	
}
