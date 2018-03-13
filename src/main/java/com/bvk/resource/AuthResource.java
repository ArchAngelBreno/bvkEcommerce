package com.bvk.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvk.dto.EmailDTO;
import com.bvk.security.JWTUtil;
import com.bvk.security.UserSS;
import com.bvk.service.AuthService;
import com.bvk.service.UserService;

@RestController
@RequestMapping("/v1/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	@PostMapping("/refreshToken")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization","Bearer "+ token);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<Void> forgotPassword(@Valid @RequestBody EmailDTO email){
		service.sendNewPassword(email.getEmail());
		return ResponseEntity.noContent().build();
	}
	
}
