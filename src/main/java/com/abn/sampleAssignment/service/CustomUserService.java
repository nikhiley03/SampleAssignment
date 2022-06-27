package com.abn.sampleAssignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abn.sampleAssignment.config.SecurityConfig;
import com.abn.sampleAssignment.model.AppUser;
import com.abn.sampleAssignment.model.CustomAppUserDetails;
import com.abn.sampleAssignment.repository.UserRepo;

@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	public String addUser(AppUser user) throws Exception{
		user.setPasswd(new SecurityConfig().bCryptPasswordEncoder().encode(user.getPasswd()));
		this.userRepo.save(user);
		return "success";
	}
	
	public List<AppUser> getAllUser() throws Exception{
		List<AppUser> allUser= this.userRepo.findAll();
		return allUser;
	}
	
	public String deleteUser(String userId) throws Exception {
		this.userRepo.deleteById(userId);
		return "success";
	}
	
	public int getUserCountByPreferance(String preferance) throws Exception {
		return this.userRepo.getConuntByPreferenace(preferance);		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser user   =this.userRepo.findByUserId(username);
		if(user==null) {
			throw new UsernameNotFoundException("No User");
		}
		return new CustomAppUserDetails(user);
	}

}
