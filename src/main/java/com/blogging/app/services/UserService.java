package com.blogging.app.services;

import java.util.List;

import com.blogging.app.payloads.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO user);

	void updateUser(UserDTO user, int id);

	UserDTO getUserById(int id);

	List<UserDTO> getUsers();

	void deleteUser(int id);
}
