package com.wellsfargo.fsd.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.ims.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer>{

}
