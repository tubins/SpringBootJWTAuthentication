package com.tubz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tubz.models.AuthenticationRequest;
import com.tubz.models.AuthenticationResponse;
import com.tubz.service.MyUserDetailsService;
import com.tubz.util.JwtUtil;

@RestController
public class HelloController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService UserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword())); // varify the username
																								// and password
		} catch (BadCredentialsException exc) {
			throw new Exception("Incorrect username or password", exc);
		}

		// Read the user details
		final UserDetails userDetails = UserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		// Create JWT token using userDetails with the help of JwtUtil
		final String jwt = jwtUtil.generateToken(userDetails);

		// Return the authentication response with JWT token
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
}
