package com.blogging.app.services;

import java.util.List;

import com.blogging.app.entities.Post;
import com.blogging.app.payloads.PostDTO;

public interface PostService {
	PostDTO createPost(PostDTO postDto);
	PostDTO updatePost(PostDTO postDto, int postId);
	void deletePost(int postId);
	List<PostDTO> getAllPosts();
	PostDTO getPostById(int postId);
	List<PostDTO> getPostByCategory(int categoryId);
	List<PostDTO> getPostByUser(int userId);
}
