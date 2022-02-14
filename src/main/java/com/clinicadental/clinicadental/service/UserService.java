package com.clinicadental.clinicadental.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.clinicadental.clinicadental.entity.User;

public interface UserService extends UserDetailsService{
	
	User findByName(String Name);

}
