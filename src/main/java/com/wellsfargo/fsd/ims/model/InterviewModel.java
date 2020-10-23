package com.wellsfargo.fsd.ims.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


public class InterviewModel {


	@NotNull(message = "Interview ID is mandatory")
	private Integer interviewId;
	
	@NotNull(message = "Interviewer Name is mandatory")
	@NotBlank(message = "Interviewer Name is mandatory")
	@Size(min=5,max=30,message = "Interviewer Name is expected to be between 5 to 30 chars in length")
	private String interviewerName;
	
	@NotNull(message = "Interview Name is mandatory")
	@NotBlank(message = "Interview Name is mandatory")
	@Size(min=3,max=30,message = "Interview Name is expected to be between 3 to 30 chars in length")
	private String interviewName;
	
	@NotNull(message = "User Skill is mandatory")
	@NotBlank(message = "User Skill is mandatory")
	@Size(min=5,max=30,message = "User Skill is expected to be between 5 to 30 chars in length")
	private String usersSkills;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalTime interviewTime;
	
	@NotNull(message = "Interview Status is mandatory")
	@NotBlank(message = "Interview Status is mandatory")
	@Size(min=5,max=100,message = "Interview Status is expected to be between 5 to 100 chars in length")
	private String interviewStatus;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dateOfInterview;
	
	@NotNull(message = "Remarks is mandatory")
	@NotBlank(message = "Remarks is mandatory")
	@Size(min=5,max=100,message = "Remarks is expected to be between 5 to 100 chars in length")
	private String remarks;
	
	@Valid
	private Set<UserModel> attendee;	
	
	public Set<UserModel> getAttendee() {
		return attendee;
	}
	
	public void setAttendee(Set<UserModel> attendees) {
		this.attendee = attendees;
	}

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUsersSkills() {
		return usersSkills;
	}

	public void setUsersSkills(String usersSkills) {
		this.usersSkills = usersSkills;
	}

	public LocalTime getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(LocalTime interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public LocalDate getDateOfInterview() {
		return dateOfInterview;
	}

	public void setDateOfInterview(LocalDate dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public InterviewModel() {
		//left unimplemented
	}

	public InterviewModel(@NotNull(message = "Interview ID is mandatory") @NotNull(message = "Interview ID is mandatory") Integer interviewId,
			@NotNull(message = "Interviewer Name is mandatory") @NotBlank(message = "Interviewer Name is mandatory") @Size(min = 5, max = 30, message = "Interviewer Name is expected to be between 5 to 30 chars in length") String interviewerName,
			@NotNull(message = "Interview Name is mandatory") @NotBlank(message = "Interview Name is mandatory") @Size(min = 3, max = 30, message = "Interview Name is expected to be between 3 to 30 chars in length") String interviewName,
			@NotNull(message = "User Skill is mandatory") @NotBlank(message = "User Skill is mandatory") @Size(min = 5, max = 30, message = "User Skill is expected to be between 5 to 30 chars in length") String usersSkills,
			LocalTime interviewTime,
			@NotNull(message = "Interview Status is mandatory") @NotBlank(message = "Interview Status is mandatory") @Size(min = 5, max = 100, message = "Interview Status is expected to be between 5 to 100 chars in length") String interviewStatus,
			LocalDate dateOfInterview,
			@NotNull(message = "Remarks is mandatory") @NotBlank(message = "Remarks is mandatory") @Size(min = 5, max = 100, message = "Remarks is expected to be between 5 to 100 chars in length") String remarks) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.usersSkills = usersSkills;
		this.interviewTime = interviewTime;
		this.interviewStatus = interviewStatus;
		this.dateOfInterview = dateOfInterview;
		this.remarks = remarks;
	}
	
	public InterviewModel(@NotNull(message = "Interview ID is mandatory") @NotNull(message = "Interview ID is mandatory") Integer interviewId,
			@NotNull(message = "Interviewer Name is mandatory") @NotBlank(message = "Interviewer Name is mandatory") @Size(min = 5, max = 30, message = "Interviewer Name is expected to be between 5 to 30 chars in length") String interviewerName,
			@NotNull(message = "Interview Name is mandatory") @NotBlank(message = "Interview Name is mandatory") @Size(min = 3, max = 30, message = "Interview Name is expected to be between 3 to 30 chars in length") String interviewName,
			@NotNull(message = "User Skill is mandatory") @NotBlank(message = "User Skill is mandatory") @Size(min = 5, max = 30, message = "User Skill is expected to be between 5 to 30 chars in length") String usersSkills,
			LocalTime interviewTime,
			@NotNull(message = "Interview Status is mandatory") @NotBlank(message = "Interview Status is mandatory") @Size(min = 5, max = 100, message = "Interview Status is expected to be between 5 to 100 chars in length") String interviewStatus,
			LocalDate dateOfInterview,
			@NotNull(message = "Remarks is mandatory") @NotBlank(message = "Remarks is mandatory") @Size(min = 5, max = 100, message = "Remarks is expected to be between 5 to 100 chars in length") String remarks, Set<UserModel> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.usersSkills = usersSkills;
		this.interviewTime = interviewTime;
		this.interviewStatus = interviewStatus;
		this.dateOfInterview = dateOfInterview;
		this.remarks = remarks;
		this.attendee = attendees;
	}

	@Override
	public String toString() {
		return "InterviewModel [interviewId=" + interviewId + ", interviewerName=" + interviewerName
				+ ", interviewName=" + interviewName + ", usersSkills=" + usersSkills + ", interviewTime="
				+ interviewTime + ", interviewStatus=" + interviewStatus + ", dateOfInterview=" + dateOfInterview
				+ ", remarks=" + remarks + "]";
	}
}
