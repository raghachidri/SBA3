package com.wellsfargo.fsd.ims.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.ims.dao.UserDao;
import com.wellsfargo.fsd.ims.entity.UserEntity;
import com.wellsfargo.fsd.ims.exception.UserException;
import com.wellsfargo.fsd.ims.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userRepo;
	
	private UserEntity toUserEntity(UserModel model) {
		
		return new UserEntity(model.getUserId(),model.getFirstName(),model.getLastName(),model.getEmail(),model.getMobile());
	}
	
	private UserModel toUserModel(UserEntity entity) {
		return new UserModel(entity.getUserId(),entity.getFirstName(),entity.getLastName(),entity.getEmail(),entity.getMobile());
	}
	
	
	@Override
	@Transactional
	public UserModel add(UserModel user) throws UserException {
		if(user!=null) {
			if(userRepo.existsById(user.getUserId())) {
				throw new UserException("User Id already used!");
			}
			
			user = toUserModel(userRepo.save(toUserEntity(user)));
		}
		return user;
	}

	@Override
	@Transactional
	public UserModel save(UserModel user) throws UserException {
		if(user!=null) {
			if(!userRepo.existsById(user.getUserId())) {
				throw new UserException("User Id does not exist");
			}
			
			user = toUserModel(userRepo.save(toUserEntity(user)));
		}
		return user;
	}

	@Override
	@Transactional
	public boolean deleteUser(Integer userId) throws UserException {
		if(!userRepo.existsById(userId)) {
			throw new UserException("User Not Found");
		}
		
		userRepo.deleteById(userId);
		return true;
	}

	@Override
	public UserModel getUser(Integer userId) {
		UserEntity entity = userRepo.findById(userId).orElse(null);
		return entity!=null?toUserModel(entity):null;
	}

	@Override
	public List<UserModel> getAllUser() {
		List<UserEntity> entities = userRepo.findAll();
		List<UserModel> models = null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toUserModel(e)).collect(Collectors.toList());
			/*models = new ArrayList<>();
			for(LoanEntity e : entities) {
				models.add(toModel(e));
			}*/
		}
		return models;
	}

}
