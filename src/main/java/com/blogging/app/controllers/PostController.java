package com.blogging.app.controllers;

import java.util.List;

import com.blogging.app.payloads.PostResponse;
import com.blogging.app.utils.ApplicationConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<PostResponse> getPostsByUser(
			@PathVariable int userId,
			@RequestParam(value = "pageNumber", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false)
			Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false)
			Integer pageSize
	) {
		PostResponse posts = postService.getPostByUser(userId, pageNumber, pageSize);
		
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(
			@PathVariable int categoryId,
			@RequestParam(value = "pageNumber", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false)
			Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false)
			Integer pageSize
	) {
		PostResponse posts = postService.getPostByCategory(categoryId, pageNumber, pageSize);
		
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false)
			Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false)
			Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY_POSTS, required = false)
			String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_BY_DIR_POSTS, required = false)
			String sortDirection
	) {
		return new ResponseEntity<PostResponse>(
				postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection),
				HttpStatus.OK
		);
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

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(
		@PathVariable String keywords
	) {
		return new ResponseEntity<>(
				postService.searchPost(keywords),
				HttpStatus.OK
		);
	}
}
