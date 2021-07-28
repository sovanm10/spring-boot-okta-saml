package com.saml.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;
import java.util.List;

import org.opensaml.saml2.core.Attribute;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="${spring.data.rest.base-path}", produces = APPLICATION_JSON_VALUE)
public class StudentController {

	/**
	 * return list of student names
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping("/students")
	public ResponseEntity<List<String>> getAllStudentName() {
		String []array = new String[] {"Abhi","Swapan","Amit"};
		List<String> list = Arrays.asList(array);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/user")
	public String index(ExpiringUsernameAuthenticationToken token) {
		SAMLCredential credential =  (SAMLCredential)token.getCredentials();
		List<Attribute> attributes = credential.getAttributes();
		attributes.forEach(k->{
			System.out.println("Name: "+k.getName());
			System.out.println("Name: "+credential.getAttributeAsString(k.getName()));
		});
		return "Hello";
	}
	
	
	
}
