package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ExecutiveDTO;
import com.example.demo.repository.ExecutiveJpaRepository;
import org.springframework.security.core.userdetails.User;

@Service
public class ExecutiveService implements UserDetailsService {
	
	@Autowired
	private ExecutiveJpaRepository executiveJpaRepository;

	
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();	

	 @PostConstruct
	    public void init() {
		 executiveJpaRepository.save(new ExecutiveDTO("user", encoder.encode("password"),true,"USER"));
		 executiveJpaRepository.save(new ExecutiveDTO("admin", encoder.encode("password"),true,"ADMIN"));
		 executiveJpaRepository.save(new ExecutiveDTO("booggie", encoder.encode("password"),true,"ADMIN"));
	       System.out.println("+++++++++++++++++++++++post construct created executive++++++++++++++++++++++++");
	      
	       for (ExecutiveDTO executiveDTO : executiveJpaRepository.findAll()) {
	    	   System.out.println(" USERNAME "+executiveDTO.getUsername()+ " PASSWORD "+executiveDTO.getPassword() );
	       }
	    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ExecutiveDTO user = executiveJpaRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Opps! user not found with user-name: " + username);
		}
		return new User(user.getUsername(), user.getPassword(),getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(ExecutiveDTO user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = AuthorityUtils.createAuthorityList(user.getRole());
		return authorities;
	}
}