package com.wellsfargo.fsd.ims.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.ims.exception.UserException;
import com.wellsfargo.fsd.ims.model.UserModel;
import com.wellsfargo.fsd.ims.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestContoller {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return new ResponseEntity<List<UserModel>>(userService.getAllUser(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getUser(@PathVariable("id")Integer userId){
		ResponseEntity<UserModel> resp=null;
		
		UserModel user = userService.getUser(userId);
		
		if(user!=null) {
			resp =new ResponseEntity<>(user,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserModel user,BindingResult result) throws UserException{
		if(result.hasErrors()) {
			throw new UserException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(userService.add(user),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UserModel> modifyUser(@RequestBody @Valid UserModel user,BindingResult result) throws UserException{
		if(result.hasErrors()) {
			throw new UserException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id")Integer userId) throws UserException{
		userService.deleteUser(userId);		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
