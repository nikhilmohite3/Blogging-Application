package com.blogging.app.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.blogging.app.payloads.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase("ASC") ?
						Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts = postRepository.findAll(pageable);
		List<Post> posts = pagePosts.getContent();
		List<PostDTO> postDTOS = posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDTOS);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());

		return postResponse;
	}

	@Override
	public PostDTO getPostById(int postId) {
		return modelMapper.map(postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post is not found with id = " + postId)),
				PostDTO.class);
	}

	@Override
	public PostResponse getPostByCategory(int categoryId, Integer pageNumber, Integer pageSize) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("Category with id " + categoryId + " does not exists."));

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = postRepository.findByCategory(category, pageable);
		//List<Post> posts = postRepository.findByCategory(category);
		List<Post> posts = pagePost.getContent();

		List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return new PostResponse(
				postDTOs, pagePost.getNumber(), pagePost.getSize(), pagePost.getTotalElements(),
				pagePost.getTotalPages(), pagePost.isLast()
		);
	}

	@Override
	public PostResponse getPostByUser(int userId, Integer pageNumber, Integer pageSize) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found"));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = postRepository.findByUser(user, pageable);
		//List<Post> posts = postRepository.findByUser(user);
		List<Post> posts = pagePost.getContent();
		List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());
		return new PostResponse(
				postDTOs, pagePost.getNumber(), pagePost.getSize(), pagePost.getTotalElements(),
				pagePost.getTotalPages(), pagePost.isLast()
		);
	}

}
