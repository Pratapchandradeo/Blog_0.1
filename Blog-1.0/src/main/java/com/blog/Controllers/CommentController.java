package com.blog.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.blog.Payloads.ApiResponse;
import com.blog.Payloads.CommentDto;
import com.blog.Services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("user/{user_Id}/post/{post_Id}")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto comment,
			@PathVariable Integer user_Id,
			@PathVariable Integer post_Id
			
			){
		CommentDto createComment = this.commentService.createComment(comment, post_Id, user_Id);
		
		
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{comment_Id}")
	public ResponseEntity<ApiResponse> deleteComment(
			@PathVariable Long comment_Id
			){
		
		this.commentService.deleteComment(comment_Id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Successfully !!!", true),HttpStatus.OK);
	}

}
