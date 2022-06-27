package com.abn.sampleAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abn.sampleAssignment.model.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, String>{
	
	@Query(value="select count(*) from users where preferance=:preferance", nativeQuery = true)
	public int getConuntByPreferenace(String preferance);
	
	public List<AppUser> findAll();
	
	public AppUser findByUserId(String userName);

}
