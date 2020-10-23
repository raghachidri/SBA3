package com.wellsfargo.fsd.ims.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name="interview")
public class InterviewEntity {

	@Id
	@GeneratedValue
	@Column(name="interviewID")
	private Integer interviewId;
	
	@Column(name="interviewerName")
	private String interviewerName;
	
	@Column(name="interviewName")
	private String interviewName;
	
	@Column(name="usersSkills")
	private String usersSkills;
	
	@Column(name="interviewTime")
	private LocalTime interviewTime;
	
	@Column(name="interviewStatus")
	private String interviewStatus;
	
	@Column(name="doi")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dateOfInterview;
	
	@Column(name="remarks")
	private String remarks;
	
	 @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	    @JoinTable(name = "InterviewSchedule", 
	      joinColumns = @JoinColumn(name = "interviewId", referencedColumnName = "interviewID"), 
	      inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "userID"))
		private Set<UserEntity> attendees=new HashSet<>();

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

	public InterviewEntity() {
		//left unimplemented
	}

	public InterviewEntity(Integer interviewId, String interviewerName, String interviewName, String usersSkills,
			LocalTime interviewTime, String interviewStatus, LocalDate dateOfInterview, String remarks) {
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
	public InterviewEntity(Integer interviewId, String interviewerName, String interviewName, String usersSkills,
			LocalTime interviewTime, String interviewStatus, LocalDate dateOfInterview, String remarks, Set<UserEntity> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.usersSkills = usersSkills;
		this.interviewTime = interviewTime;
		this.interviewStatus = interviewStatus;
		this.dateOfInterview = dateOfInterview;
		this.remarks = remarks;
		this.attendees =attendees;
	}

	public Set<UserEntity> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<UserEntity> attendees) {
		this.attendees = attendees;
	}
	
	@Override
	public String toString() {
		return "InterviewEntity [interviewId=" + interviewId + ", interviewerName=" + interviewerName
				+ ", interviewName=" + interviewName + ", usersSkills=" + usersSkills + ", interviewTime="
				+ interviewTime + ", interviewStatus=" + interviewStatus + ", dateOfInterview=" + dateOfInterview
				+ ", remarks=" + remarks + "]";
	}
}
