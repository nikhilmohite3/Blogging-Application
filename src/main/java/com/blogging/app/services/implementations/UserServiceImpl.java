package com.blogging.app.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blogging.app.entities.User;
import com.blogging.app.exceptions.ResourceNotFoundException;
import com.blogging.app.payloads.UserDTO;
import com.blogging.app.repositories.UserRepository;
import com.blogging.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		// TODO Auto-generated method stub
		User newUser = dtoToUser(user);
		return userToDto(userRepository.save(newUser));
	}

	@Override
	public void updateUser(UserDTO user, int id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found"));

		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setAbout(user.getAbout());

		userRepository.save(existingUser);

	}

	@Override
	public UserDTO getUserById(int id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found"));

		return userToDto(existingUser);
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> userList = userRepository.findAll();

		return userList.stream().map(user -> userToDto(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(int id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found"));
		userRepository.delete(existingUser);

	}

	public User dtoToUser(UserDTO userDTO) {
//		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(),
//				userDTO.getAbout());

		return modelMapper.map(userDTO, User.class);

	}

	public UserDTO userToDto(User user) {
//		return new UserDTO(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(),
//				userDTO.getAbout());
		return modelMapper.map(user, UserDTO.class);
	}
}
