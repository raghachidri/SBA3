package com.wellsfargo.fsd.ims.model;

import javax.validation.constraints.NotNull;

public class InterviewAttendeeModel {


	@NotNull(message = "Interview Id is mandatory")
	private Integer interviewId;
	
	@NotNull(message = "User Id is mandatory")
	private Integer userId;
	
	public InterviewAttendeeModel() {
		//left unimplemented
	}

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public InterviewAttendeeModel(@NotNull(message = "Interview Id is mandatory") Integer interviewId,
			@NotNull(message = "User Id is mandatory") Integer userId) {
		super();
		this.interviewId = interviewId;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AttendeeModel [interviewId=" + interviewId + ", userId=" + userId + "]";
	}	




}
