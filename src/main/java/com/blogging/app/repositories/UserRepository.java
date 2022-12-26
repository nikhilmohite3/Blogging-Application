package com.blogging.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.app.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
