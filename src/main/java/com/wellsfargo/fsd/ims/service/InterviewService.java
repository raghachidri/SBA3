package com.wellsfargo.fsd.ims.service;

import java.util.Set;

import com.wellsfargo.fsd.ims.exception.UserException;
import com.wellsfargo.fsd.ims.model.InterviewAttendeeModel;
import com.wellsfargo.fsd.ims.model.InterviewModel;
import com.wellsfargo.fsd.ims.model.UserModel;


public interface InterviewService {
	
	InterviewModel add(InterviewModel interview) throws UserException;

	InterviewModel save(InterviewModel interview) throws UserException;

	boolean deleteInterview(Integer interviewId) throws UserException;

	//InterviewModel getInterview(Integer interviewId);

	
	InterviewModel updateStatus(Integer interviewid, String status) throws UserException;
	
	Set<InterviewModel> getinterview(String interviewName, String interviewerName);
	
	String getInterviewCount();
	
	Set<InterviewModel> getAllInterviewDetails();
	
	Set<UserModel> showUsers(int interviewId) throws UserException;

	String addAttendee(InterviewAttendeeModel attendee) throws UserException;

	InterviewModel getInterviewById(int interviewId);
}
