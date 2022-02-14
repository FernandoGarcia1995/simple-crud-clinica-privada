package com.clinicadental.clinicadental.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinicadental.clinicadental.entity.Rol;
import com.clinicadental.clinicadental.entity.User;
import com.clinicadental.clinicadental.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByName(String Name) {
		return userRepository.findByUsername(Name);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRol()));
	}
	
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Rol role){
    	Collection<GrantedAuthority> authorities
        = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority(role.getNombre()));
        return authorities;
    }
	
}
