package com.blogging.app.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.app.entities.Category;
import com.blogging.app.entities.Post;
import com.blogging.app.entities.User;
import com.blogging.app.exceptions.ResourceNotFoundException;
import com.blogging.app.payloads.PostDTO;
import com.blogging.app.repositories.CategoryRepository;
import com.blogging.app.repositories.PostRepository;
import com.blogging.app.repositories.UserRepository;
import com.blogging.app.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private UserRepository userRepository;

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;

	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository,
			CategoryRepository categoryRepository) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
	}

	@Override
	public PostDTO createPost(PostDTO postDto, int userId, int categoryId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found"));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category with given ID Not Found"));

		Post newPost = modelMapper.map(postDto, Post.class);
		newPost.setImageName("default.png");
		newPost.setDate(new Date());
		newPost.setCategory(category);
		newPost.setUser(user);

		return modelMapper.map(postRepository.save(newPost), PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, int postId) {
		return null;
	}

	@Override
	public void deletePost(int postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public List<PostDTO> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public PostDTO getPostById(int postId) {
		return modelMapper.map(postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post is not found with id = " + postId)),
				PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("Category with id " + categoryId + " does not exists."));

		List<Post> posts = postRepository.findByCategory(category);
		List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> getPostByUser(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found"));
		List<Post> posts = postRepository.findByUser(user);
		return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

	}

}
