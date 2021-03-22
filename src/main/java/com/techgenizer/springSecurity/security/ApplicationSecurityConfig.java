package com.techgenizer.springSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public  ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
			.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINEE.name())
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails danny = User.builder()
								.username("danny")
								.password(passwordEncoder.encode("danny"))
//								.roles(ApplicationUserRole.STUDENT.name())
								.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
								.build();
		
		UserDetails jimmy = User.builder()
								.username("jimmy")
								.password(passwordEncoder.encode("jimmy"))
//								.roles(ApplicationUserRole.ADMIN.name())
								.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
								.build();
		
		UserDetails van = User.builder()
								.username("van")
								.password(passwordEncoder.encode("van"))
//								.roles(ApplicationUserRole.ADMINTRAINEE.name())
								.authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
								.build();
		
		return new InMemoryUserDetailsManager(danny,jimmy,van);
	}
}
