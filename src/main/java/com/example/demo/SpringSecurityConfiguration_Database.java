package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.example.demo.service.ExecutiveService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration_Database extends WebSecurityConfigurerAdapter {

	@Autowired
	private ExecutiveService executiveService;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(executiveService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
		 * STATELESS) .and().authorizeRequests()
		 * .antMatchers("/api/user/**").authenticated()
		 * .and().httpBasic().realmName("User Registration System")
		 * .and().csrf().disable();
		 */

		http
	      .httpBasic().and()
	      .authorizeRequests()
	        .antMatchers("template/login.html").permitAll()
	        .anyRequest()
	        .authenticated().and()
		.csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

}
