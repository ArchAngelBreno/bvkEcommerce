package com.bvk.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bvk.dto.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authManager;
	private JWTUtil jwtUtil;

	@Autowired
	public JWTAuthenticationFilter(AuthenticationManager authManager, JWTUtil jwtUtil) {
		super();
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			CredentialsDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword(),new ArrayList<>());
			Authentication auth = authManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String username= ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Authorization", "Bearer "+token);
		
		
	}
	
}
