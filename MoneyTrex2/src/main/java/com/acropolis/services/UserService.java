package com.acropolis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acropolis.entity.Users;
import com.acropolis.models.LoginModel;
import com.acropolis.models.UserModel;
import com.acropolis.repositories.UserRepository;

@Service
public class UserService  implements UserDetailsService{
	@Autowired
	private UserRepository urepo;
	
	public Users saveUser(UserModel model) {
		try {
			Users user=new Users(model);
			user.setRole("ROLE_CUSTOMER");
			return urepo.save(user);
		}catch(Exception e) {
			return null;
		}
	}

	public Users checkUser(LoginModel model) {
		Optional<Users> opt=urepo.findByEmailAndPassword(model.getEmail(),model.getPassword());
		if(opt.isPresent()) {
			return opt.get();
		}
		else
			return null;
	}
	
	public List<Users> custlist(char ch){
		if(ch=='C' || ch=='c')
			return urepo.findByRole("ROLE_CUSTOMER");
		else
			return urepo.findByRole("ROLE_ADMIN");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> opt=urepo.findByEmail(username);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
			return null;

	}

	public Users get(Integer uid) {
		try {
			return urepo.findById(uid).get();
		}catch(Exception e) {
			return null;
		}
	}

	public void update(Users user) {
		urepo.save(user);
		
	}
	
}
