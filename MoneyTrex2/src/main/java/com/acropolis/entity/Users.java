package com.acropolis.entity;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.acropolis.models.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users implements UserDetails {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer userid;
		@Column(nullable = false)
		private String name;
		@Column(nullable = false,unique = true)
		private String email;
		@Column(nullable = false)
		private String password;
		@Column(nullable = false)
		private String role;
		private Boolean status;
		public Users(String name, String email, String password, String role) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
			this.role = role;
			
		}
		public Users(UserModel model) {
			this.name=model.getName();
			this.email=model.getEmail();
			this.password=model.getPassword();
			this.status=true;
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			SimpleGrantedAuthority sga=new SimpleGrantedAuthority(role);
			return Arrays.asList(sga);
		}
		@Override
		public String getUsername() {
			return email;
		}
		public boolean getStatus() {
			// TODO Auto-generated method stub
			return false;
		}
		
		
}
