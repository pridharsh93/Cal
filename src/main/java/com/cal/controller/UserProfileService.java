package com.cal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cal.model.UserProfile;
import com.cal.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserProfileService {
	
	@Autowired
	private IUserService iUserService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> get() {
		String str= "welcome to cal";
		return new ResponseEntity<String>(str, HttpStatus.OK);

	}

	
	@GetMapping("get/{id}")
	public ResponseEntity<UserProfile> getUserByID(@PathVariable("id") Integer id) {

		UserProfile userProfile = iUserService.getUserByID(id);
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<Void> addUser(@RequestBody UserProfile profile,UriComponentsBuilder builder){
		
		boolean flag = iUserService.addUserProfile(profile);
		  if (flag == false) {
      	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
              }
          return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@PutMapping
	public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile profile ){
		iUserService.updateUserprofile(profile);
	return new ResponseEntity<UserProfile>(profile, HttpStatus.OK);
	}
	
	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteUserProfile(@PathVariable("id") Integer id) {
		
		iUserService.deleteUserProfile(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	
}

}
