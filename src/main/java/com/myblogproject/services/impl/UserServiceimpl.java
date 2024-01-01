package com.myblogproject.services.impl;
import com.myblogproject.entities.User;
import com.myblogproject.config.AppConstants;
import com.myblogproject.entities.Category;
import com.myblogproject.entities.Role;
import com.myblogproject.exceptions.*;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myblogproject.payloads.UserDto;
import com.myblogproject.repositories.RoleRepo;
import com.myblogproject.repositories.UserRepo;
import com.myblogproject.services.UserService;



@Service
public class UserServiceimpl implements UserService {

//	iska use ham jab krte h jab ham esi id dalte h 
//	jo exist nahi krti jo found nhi h agr vo id nahi hoti h to error nai ae
//	error ko handle karne ke liye iska use krte h
	
	
//	User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
//	create user------------------------
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

//	update user---------------------------
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
//		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User userupdated=this.userRepo.save(user);
		
		UserDto userDto1=this.userToDto(userupdated);
		
		
		return userDto1;
	}

//	get user by id-----------------------
	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

//	get alluser------------------------------
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

//	delete user-----------------------------
	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
	

//	---------------yha ham manually convert kr rhe h userdto ko userentity me
//	private User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		return user;
//		
//	}
	

	
//	==========yha hamare ps kam fields h to manually easy handle kr skte h par next stage m jda methods ho skti h or isko manually handle nahi kr skte isliye  
//	model mapper ka use krna h=================

	//	--------------------yha  ham manually userentity ko  user dto me conver kr rhe h 
	
//	public UserDto userToDto(User user) {
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//	    return userDto;
//	}
//
//	
//============use modelmapper===========
//	I have to convert userdto to user.class----------
	public User dtoUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		
	    return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		
		
//		encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		
//		roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		
		return this.modelMapper.map(newUser,UserDto.class);
	}
}
