package com.acropolis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Users;
import com.acropolis.models.ExpenseLimiterModel;
import com.acropolis.repositories.ExpenseLimiterRepository;

@Service
public class ExpenseLimiterService {
	@Autowired
	private ExpenseLimiterRepository erep;

	public ExpenseLimiters save(ExpenseLimiterModel model) {
		try {
			ExpenseLimiters exp=new ExpenseLimiters(model);
			Users userob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			exp.setUser(userob);
			return erep.save(exp);
		}catch(Exception e) {
			return null;
		}
	}

	public ExpenseLimiters get(int id) {
		try {
			return erep.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}

	public ExpenseLimiters update(ExpenseLimiters exp) {
		return erep.save(exp);
	}

	public void delete(ExpenseLimiters exp) {
		erep.delete(exp);
		
	}

	public ExpenseLimiters get() {
		Users user=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			Optional<ExpenseLimiters> op=erep.findByUser(user);
			if(op.isPresent())
				return op.get();
			else
				return null;			
		}catch(Exception e) {
			return null;
		}
	}
	
	
	
	
	
	
	
	
}
