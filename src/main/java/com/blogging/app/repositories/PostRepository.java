package com.blogging.app.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.app.entities.Category;
import com.blogging.app.entities.Post;
import com.blogging.app.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	Page<Post> findByUser(User user, Pageable pageable);
	Page<Post> findByCategory(Category category, Pageable pageable);
}
