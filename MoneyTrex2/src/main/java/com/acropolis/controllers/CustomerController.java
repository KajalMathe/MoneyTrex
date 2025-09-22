package com.acropolis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;
import com.acropolis.models.ExpenseLimiterModel;
import com.acropolis.models.SearchModel;
import com.acropolis.models.TransactionModel;
import com.acropolis.services.ExpenseLimiterService;
import com.acropolis.services.TransactionService;
import com.acropolis.util.APIResponse;

@CrossOrigin
@RestController
@RequestMapping("/auth/cust")
public class CustomerController {
	@Autowired
	private TransactionService tser;
	@Autowired
	private ExpenseLimiterService exser;
	

	@GetMapping("/home")
	public String home() {
		return "Hello How r u?";
	}
	
	@PostMapping("/savetrans")
	public ResponseEntity<APIResponse> save(@RequestBody TransactionModel model){
		Transaction tr= tser.save(model);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transaction Saved....", true, tr));
		else
			return ResponseEntity.ok(new APIResponse("Transaction Could not Saved....", false, null));
					
	}
	
	@GetMapping("/gettrans/{id}")
	public ResponseEntity<APIResponse> save(@PathVariable("id") int id){
		Transaction tr= tser.get(id);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transaction Found....", true, tr));
		else
			return ResponseEntity.ok(new APIResponse("Transaction Not found....", false, null));
					
	}
	
	
	@PutMapping("/updtrans/{id}")
	public ResponseEntity<APIResponse> save(@PathVariable("id") int id,@RequestBody TransactionModel model){
		Transaction tr= tser.get(id);
		if(tr!=null) {
			
			tr.update(model);
			tr=tser.update(tr);
			return ResponseEntity.ok(new APIResponse("Transaction Updated....", true, tr));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transaction Not Update....", false, null));
					
	}
	@GetMapping("/translist")
	public ResponseEntity<APIResponse> list(){
		Users userob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Transaction> list= tser.list(userob);
		if(list!=null)
			return ResponseEntity.ok(new APIResponse("Transaction List....", true, list));
		else
			return ResponseEntity.ok(new APIResponse("Transaction Not found....", false, null));
					
	}
	
	@DeleteMapping("/deltrans/{id}")
	public ResponseEntity<APIResponse> delete(@PathVariable("id") int id){
		Transaction tr= tser.get(id);
		if(tr!=null) {
			tser.delete(tr);
			return ResponseEntity.ok(new APIResponse("Transaction Removed....", true,null));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transaction Could not removed....", false, null));
					
	}
	
	
	
	@PostMapping("/savelimit")
	public ResponseEntity<APIResponse> saveLimit(@RequestBody ExpenseLimiterModel model){
		ExpenseLimiters exp= exser.save(model);
		if(exp!=null)
			return ResponseEntity.ok(new APIResponse("Limit is set now", true, exp));
		else
			return ResponseEntity.ok(new APIResponse("Can't set the limit", false, null));
	}
	
	@GetMapping("/getlimit")
	public ResponseEntity<APIResponse> saveLimit(){
		ExpenseLimiters exp= exser.get();
		if(exp!=null)
			return ResponseEntity.ok(new APIResponse("Limit is found", true, exp));
		else
			return ResponseEntity.ok(new APIResponse("Limit not found...", false, null));
	}
	
	@PutMapping("/updlimit/{id}/{limit}")
	public ResponseEntity<APIResponse> save(@PathVariable("id") int id,@PathVariable("limit") int limit){
		ExpenseLimiters exp= exser.get(id);
		if(exp!=null) {
			exp.setLimits(limit);
			exp= exser.update(exp);
			return ResponseEntity.ok(new APIResponse("Limit Updated.....", true, exp));
		}
		else
			return ResponseEntity.ok(new APIResponse("Limit could not update", false, null));
					
	}
	
	@DeleteMapping("/dellimit/{id}")
	public ResponseEntity<APIResponse> deleteLimit(@PathVariable("id") int id){
		ExpenseLimiters exp= exser.get(id);
		if(exp!=null) {
			exser.delete(exp);
			return ResponseEntity.ok(new APIResponse("Limit Removed....", true,null));
		}
		else
			return ResponseEntity.ok(new APIResponse("Limit Could not removed....", false, null));
					
	}
	
	@PostMapping("/searchtrans")
	public ResponseEntity<APIResponse> search(@RequestBody SearchModel model){
		List<Transaction> list=tser.search(model);
		if(list!=null)
			return ResponseEntity.ok(new APIResponse("Tranasaction found...", true,list));
		else
			return ResponseEntity.ok(new APIResponse("Transaction Not found.....", false, null));
	}

	
	
}
