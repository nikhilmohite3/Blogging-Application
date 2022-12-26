package com.blogging.app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.app.payloads.PostDTO;
import com.blogging.app.repositories.PostRepository;
import com.blogging.app.services.PostService;

@RestController
@RequestMapping("/")
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<?> createPost(
			@RequestBody PostDTO postDTO,
			@PathVariable int userId, 
			@PathVariable int categoryId) {
		
		PostDTO post = postService.createPost(postDTO, userId, categoryId);
		
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable int userId) {
		List<PostDTO> posts = postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable int categoryId) {
		List<PostDTO> posts = postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts() {
		return new ResponseEntity<List<PostDTO>>( postService.getAllPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable int postId){
		return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePostById(@PathVariable int postId) {
		 postService.deletePost(postId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
