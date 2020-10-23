package com.wellsfargo.fsd.ims.service;

import java.util.List;

import com.wellsfargo.fsd.ims.exception.UserException;
import com.wellsfargo.fsd.ims.model.UserModel;

public interface UserService {

	UserModel add(UserModel user) throws UserException;
	UserModel save(UserModel user) throws UserException;
	
	boolean deleteUser(Integer userId) throws UserException;
	
	UserModel getUser(Integer userId);
	List<UserModel> getAllUser();
}
