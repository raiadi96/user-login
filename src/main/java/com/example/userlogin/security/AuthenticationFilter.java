package com.example.userlogin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.userlogin.SpringApplicationContext;
import com.example.userlogin.dto.UserDto;
import com.example.userlogin.model.request.UserLoginRequestModel;
import com.example.userlogin.services.implementation.UserService;
import com.example.userlogin.services.interfaces.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager _authenticationManager;
	private final String user_service_bean = "userService";

	public AuthenticationFilter(AuthenticationManager _authenticationManager) {
		super();
		this._authenticationManager = _authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
			return _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
	
		}
		catch(IOException  e){
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userName = ((User)authResult.getPrincipal()).getUsername();
		String token = Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();
		IUserService userService = (UserService)SpringApplicationContext.getBean(user_service_bean);
		UserDto user_details = userService.getUserByEmail(userName);
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("Public User Id", user_details.getUser_id());
		
	}
	

}
