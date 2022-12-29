package com.blogging.app.services;

import java.util.List;

import com.blogging.app.entities.Post;
import com.blogging.app.payloads.PostDTO;
import com.blogging.app.payloads.PostResponse;

public interface PostService {
	PostDTO createPost(PostDTO postDto, int userId, int categoryId);
	PostDTO updatePost(PostDTO postDto, int postId);
	void deletePost(int postId);
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	PostDTO getPostById(int postId);
	PostResponse getPostByCategory(int categoryId, Integer pageNumber, Integer pageSize);
	PostResponse getPostByUser(int userId, Integer pageNumber, Integer pageSize);
}
