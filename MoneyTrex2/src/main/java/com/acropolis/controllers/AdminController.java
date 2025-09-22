package com.acropolis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;
import com.acropolis.services.TransactionService;
import com.acropolis.services.UserService;
import com.acropolis.util.APIResponse;

@CrossOrigin
@RestController
@RequestMapping("auth/admin")
public class AdminController {
	@Autowired
	private UserService userServ;
	@Autowired
	private TransactionService tser;
	
	@GetMapping("/home")
	public String home() {
		return "Hello!!!!";
	}
	
	@GetMapping("/userdata")
	public ResponseEntity<APIResponse> userdata(){
		List<Users> users= userServ.custlist('C');
		if(users!=null) 
			return ResponseEntity.ok(new APIResponse("List of Users",true,users));
		else 
			return ResponseEntity.ok(new APIResponse("No Users are available",false,null));
	}	

	@PutMapping("/changestatus/{uid}")
	public ResponseEntity<APIResponse> change(@PathVariable(value = "uid") Integer uid)
	{
		System.out.println(uid);
		Users user=userServ.get(uid);		
		user.setStatus(!user.getStatus());
		userServ.update(user);
		return ResponseEntity.ok(new APIResponse("Status changed..",true,user));
			
	}	
	
	@GetMapping("/translist/{uid}")
	public ResponseEntity<APIResponse> list(@PathVariable(value="uid") Integer uid)
	{
	Users user=userServ.get(uid);	
	List<Transaction>list= tser.list(user);
	if(list!=null) 
		return ResponseEntity.ok(new APIResponse("List of Transaction",true,list));
	else 
		return ResponseEntity.ok(new APIResponse("Transaction not found",false,null));
	
	}



}
