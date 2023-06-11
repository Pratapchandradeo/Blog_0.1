package com.blog.Services;

import com.blog.Payloads.CommentDto;



public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postID, Integer userID);
	
	void deleteComment(Long commentID);
	

}
