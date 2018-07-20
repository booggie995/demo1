package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class SpringSecurityConfigurationInMemory extends WebSecurityConfigurerAdapter {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user/").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/user/").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/user/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                .and().logout().permitAll()
                .and().csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password(encoder.encode("password")).roles("USER").build();

        UserDetails admin = User.withUsername("admin").password(encoder.encode("password")).roles("USER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
