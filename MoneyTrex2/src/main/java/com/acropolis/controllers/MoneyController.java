package com.acropolis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acropolis.configs.JWTUtils;
import com.acropolis.entity.Users;
import com.acropolis.models.LoginModel;
import com.acropolis.models.LoginResponseModel;
import com.acropolis.models.UserModel;
import com.acropolis.services.UserService;
import com.acropolis.util.APIResponse;

@CrossOrigin
@RestController
@RequestMapping("/money")
public class MoneyController {
	public static APIResponse resp=null;
	@Autowired
	private UserService userv;
	@Autowired
	private JWTUtils jwt;
	
	@PostMapping("/save")
	public ResponseEntity<APIResponse> save(@RequestBody UserModel model) {
		Users ob=userv.saveUser(model);
		if(ob!=null) {
			resp=new APIResponse("User Added Successfully...",true,ob);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		}else {
			resp=new APIResponse("user could not be added....", false, null);
			return ResponseEntity.ok(resp);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginModel model){
		Users ob=userv.checkUser(model);
		if(ob!=null) {
			String token=jwt.generateToken(ob.getEmail());
			LoginResponseModel logmodel=new LoginResponseModel(ob.getName(),ob.getRole(), token);
			resp=new APIResponse("Welcome to Money Trex", true, logmodel);
			return ResponseEntity.ok(resp);
		}
		else {
			return ResponseEntity.ok(new APIResponse("Invalid Credentials....", false, null));
		}
	}
	
	
//	@GetMapping("/custlist")
//	public ResponseEntity<APIResponse> list(){
//		List<Users> list=userv.list();
//		return ResponseEntity.ok(new APIResponse("List of Customers", true, list));
//	}
	
	@GetMapping("/wrongauth")
	public ResponseEntity<APIResponse> wrong(){
		return ResponseEntity.ok(new APIResponse("Unauthorised User....", false, null));
	}
	
	
}










