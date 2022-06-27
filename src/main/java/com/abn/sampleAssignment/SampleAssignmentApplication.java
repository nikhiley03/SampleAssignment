package com.abn.sampleAssignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.abn.sampleAssignment.config.SecurityConfig;
import com.abn.sampleAssignment.model.AppUser;
import com.abn.sampleAssignment.repository.UserRepo;

@SpringBootApplication
public class SampleAssignmentApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(SampleAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// TODO Auto-generated method stub
		AppUser admin = new AppUser();
		admin.setFirstName("Admin");
		admin.setLastName("Admin");
		admin.setPreferance("Non Veg");
		admin.setRole("Admin");
		admin.setUserId("Admin");
		admin.setPasswd(new SecurityConfig().bCryptPasswordEncoder().encode("Admin"));
		userRepo.save(admin);
		AppUser normal = new AppUser();
		normal.setFirstName("Normal");
		normal.setLastName("Normal");
		normal.setPreferance("Non Veg");
		normal.setRole("Normal");
		normal.setUserId("Normal");
		normal.setPasswd(new SecurityConfig().bCryptPasswordEncoder().encode("Normal"));
		userRepo.save(normal);

	}

}
