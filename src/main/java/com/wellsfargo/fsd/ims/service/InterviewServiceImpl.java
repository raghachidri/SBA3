package com.wellsfargo.fsd.ims.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.ims.dao.InterviewDao;
import com.wellsfargo.fsd.ims.dao.UserDao;
import com.wellsfargo.fsd.ims.entity.InterviewEntity;
import com.wellsfargo.fsd.ims.entity.UserEntity;
import com.wellsfargo.fsd.ims.exception.UserException;
import com.wellsfargo.fsd.ims.model.InterviewAttendeeModel;
import com.wellsfargo.fsd.ims.model.InterviewModel;
import com.wellsfargo.fsd.ims.model.UserModel;


@Service
public class InterviewServiceImpl implements InterviewService {

	
	@Autowired
	private InterviewDao interviewRepo;
	
	@Autowired
	private UserDao userRepo;
	
	private InterviewEntity toEntity(InterviewModel model) {

		if (model.getAttendee() == null)
			return new InterviewEntity(model.getInterviewId(), model.getInterviewerName(), model.getInterviewName(),
					model.getUsersSkills(), model.getInterviewTime(), model.getInterviewStatus(),
					model.getDateOfInterview(), model.getRemarks());
		else
			return new InterviewEntity(model.getInterviewId(), model.getInterviewerName(), model.getInterviewName(),
					model.getUsersSkills(), model.getInterviewTime(), model.getInterviewStatus(),
					model.getDateOfInterview(), model.getRemarks(), toUserEntities(model.getAttendee()));

	}
	
	private InterviewModel toModel(InterviewEntity entity) {

		if(entity.getAttendees()==null)
			return new InterviewModel(entity.getInterviewId(),entity.getInterviewerName(),entity.getInterviewName(),entity.getUsersSkills(),
					entity.getInterviewTime(),entity.getInterviewStatus(),entity.getDateOfInterview(),entity.getRemarks());
		else
			return new InterviewModel(entity.getInterviewId(),entity.getInterviewerName(),entity.getInterviewName(),entity.getUsersSkills(),
					entity.getInterviewTime(),entity.getInterviewStatus(),entity.getDateOfInterview(),entity.getRemarks(), toUserModels(entity.getAttendees()));
	
	}
	
	private Set<UserEntity> toUserEntities(Set<UserModel> userModels) {
		Set<UserEntity> entities=null;
		entities = userModels.stream().map(e -> toUserEntity(e)).collect(Collectors.toSet());
		return entities;
	}
	
	private UserEntity toUserEntity(UserModel model) {
		return new UserEntity(model.getUserId(), model.getFirstName(), model.getLastName(),model.getEmail(), model.getMobile());
	}
	
	private Set<UserModel> toUserModels(Set<UserEntity> userEntities) {
		Set<UserModel> models=null;
		models = userEntities.stream().map(e -> toUserModel(e)).collect(Collectors.toSet());
		return models;
	}
	
	private UserModel toUserModel(UserEntity entity) {
		return new UserModel(entity.getUserId(), entity.getFirstName(), entity.getLastName(),entity.getEmail(), entity.getMobile());
	}
	
	@Override
	@Transactional
	public InterviewModel add(InterviewModel interview) throws UserException {
		if(interview!=null) {
			if(interviewRepo.existsById(interview.getInterviewId())) {
				throw new UserException("Interview Id already used!");
			}
			
			interview = toModel(interviewRepo.save(toEntity(interview)));
		}
		return interview;
	}
	
	@Override
	@Transactional
	public String addAttendee(InterviewAttendeeModel attendee) throws UserException {
		if(attendee!=null) {
			if(!userRepo.existsById(attendee.getUserId())) {
				throw new UserException("User Not Found");
			}
	        if(!interviewRepo.existsById(attendee.getInterviewId())) {
	            throw new UserException("Interview Id Not Found!");
	        }
	        InterviewModel interview = getInterviewById(attendee.getInterviewId());
	        for(UserModel user: interview.getAttendee()) {
	        	if(user.getUserId() == attendee.getUserId()) {
	        		throw new UserException("User Id already exists on Interview!");
	        	}
	        }
	        Set<UserModel> users=interview.getAttendee();	        
	        users.add(getUserById(attendee.getUserId()));
	        interview.setAttendee(users);
	        interviewRepo.save(toEntity(interview));
	        return "User with Id: " +  getUserById(attendee.getUserId()) + " added successfully to Interview";
		}
		return "Error adding User with Id: " +  attendee.getUserId();
	}
	public UserModel getUserById(int userId) {
	    UserEntity entity = userRepo.findById(userId).orElse(null);
	    return entity!=null?toUserModel(entity):null;
	}
	
	@Override
	public InterviewModel updateStatus(Integer interviewId, String status) throws UserException {
		if(!interviewRepo.existsById(interviewId)) {
			throw new UserException("Interview Id Not Found!");
		}
		InterviewModel interview = getInterviewById(interviewId);
		interview.setInterviewStatus(status);
		interviewRepo.save(toEntity(interview));
		return getInterviewModel(toEntity(interview));
	}
	
	private InterviewModel getInterviewModel(InterviewEntity entity) {
		return new InterviewModel(entity.getInterviewId(),entity.getInterviewerName(),entity.getInterviewName(),entity.getUsersSkills(),
				entity.getInterviewTime(),entity.getInterviewStatus(),entity.getDateOfInterview(),entity.getRemarks());
	}
	
	@Override
	public Set<InterviewModel> getinterview(String interviewName, String interviewerName) {
		Set<InterviewEntity> entities= new HashSet<InterviewEntity>(interviewRepo.findByNameAndInterviewer(interviewName, interviewerName));
		Set<InterviewModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toSet());
		}
		return models;
	}

	@Override
	public String getInterviewCount() {
		Set<InterviewEntity> entities=  new HashSet<InterviewEntity>(interviewRepo.findAll());
		if(entities!=null)
			return "Total no. of Interviews: " + entities.size();
		else
			return "No interviews are present";
	}

	@Override
	public Set<InterviewModel> getAllInterviewDetails() {
		Set<InterviewEntity> entities= new HashSet<InterviewEntity>(interviewRepo.findAll());
		Set<InterviewModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toSet());
		}
		return models;
	}

	@Override
	public Set<UserModel> showUsers(int interviewId) throws UserException {
		if(!interviewRepo.existsById(interviewId)) {
            throw new UserException("Interview Id Not Found!");
        }
		return toUserModels(interviewRepo.findById(interviewId).orElse(null).getAttendees());
	}

	@Override
	public InterviewModel getInterviewById(int iId) {
        InterviewEntity entity = interviewRepo.findById(iId).orElse(null);
        return entity!=null?toModel(entity):null;
    }
	
	@Override
	@Transactional
	public InterviewModel save(InterviewModel interview) throws UserException {
		if(interview!=null) {
			if(!interviewRepo.existsById(interview.getInterviewId())) {
				throw new UserException("Interview Id does not exist");
			}
			
			interview = toModel(interviewRepo.save(toEntity(interview)));
		}
		return interview;
	}

	@Override
	@Transactional
	public boolean deleteInterview(Integer interviewId) throws UserException {
		if(!interviewRepo.existsById(interviewId)) {
			throw new UserException("Interview Not Found");
		}
		
		interviewRepo.deleteById(interviewId);
		return true;
	}

}
