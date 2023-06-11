package com.blog.Services.Implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Modules.Comment;
import com.blog.Modules.Post;
import com.blog.Modules.User;
import com.blog.Payloads.CommentDto;
import com.blog.Repositories.CommentRepo;
import com.blog.Repositories.PostRepo;
import com.blog.Repositories.UserRepo;
import com.blog.Services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postID, Integer userID) {
		
		User user = this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userID));
		
		Post post = this.postRepo.findById(postID).orElseThrow(()-> new ResourceNotFoundException("post", "post_id", postID));
		
		Comment comment = this.mapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		comment.setUser(user);
		
		Comment saveComment = this.commentRepo.save(comment);
		
		return this.mapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Long commentID) {
		Comment comment = this.commentRepo.findById(commentID).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentID));
		this.commentRepo.delete(comment);
	}

}
