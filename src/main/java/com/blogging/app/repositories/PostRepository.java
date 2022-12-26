package com.blogging.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.app.entities.Category;
import com.blogging.app.entities.Post;
import com.blogging.app.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
