package com.wellsfargo.fsd.ims.controller;

import java.util.Set;

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
import com.wellsfargo.fsd.ims.model.InterviewAttendeeModel;
import com.wellsfargo.fsd.ims.model.InterviewModel;
import com.wellsfargo.fsd.ims.model.UserModel;
import com.wellsfargo.fsd.ims.service.InterviewService;

@RestController
@RequestMapping("/interview")
public class InterviewRestController {

	@Autowired
	private InterviewService interviewService;
	
	@GetMapping
	public ResponseEntity<Set<InterviewModel>> getAllInterviews(){
		return new ResponseEntity<Set<InterviewModel>>(interviewService.getAllInterviewDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<String> getCountOfInterviews(){
		return new ResponseEntity<>(interviewService.getInterviewCount(),HttpStatus.OK);
	}

	@GetMapping("/showAttendee/{id}")
	public ResponseEntity<Set<UserModel>> showAttendees(@PathVariable("id")int interviewId) throws UserException{
		return new ResponseEntity<>(interviewService.showUsers(interviewId),HttpStatus.OK);
	}
	
	@GetMapping("/showInterviews/{InterviewName}/{InterviewerName}")
	public ResponseEntity<Set<InterviewModel>> getInterviews(@PathVariable("InterviewName")String interviewName, @PathVariable("InterviewerName")String interviewerName){
		ResponseEntity<Set<InterviewModel>> resp=null;
		Set<InterviewModel> interview = interviewService.getinterview(interviewName, interviewerName);
		
		if(interview!=null) {
			resp =new ResponseEntity<>(interview,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<InterviewModel> createInterview(@RequestBody @Valid InterviewModel interview,BindingResult result) throws UserException{
		if(result.hasErrors()) {
			throw new UserException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.add(interview),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInterview(@PathVariable("id")int interviewId) throws UserException{
		interviewService.deleteInterview(interviewId);		
		return new ResponseEntity<>("Interview Deleted Successfully",HttpStatus.OK);
	}
	
	@PutMapping("/addAttendee")
	public ResponseEntity<String> addAttendee(@RequestBody @Valid InterviewAttendeeModel attendee,BindingResult result) throws UserException{
		if(result.hasErrors()) {
			throw new UserException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.addAttendee(attendee),HttpStatus.OK);
	}
	

	@PutMapping("/updateStatus/{interviewid}")
	public ResponseEntity<InterviewModel> updateStatus1(@PathVariable("interviewid")int interviewId,@RequestBody @Valid InterviewModel interview,BindingResult result) throws UserException{
		return new ResponseEntity<>(interviewService.updateStatus(interviewId, interview.getInterviewStatus()),HttpStatus.OK);
	}
	
	
}
