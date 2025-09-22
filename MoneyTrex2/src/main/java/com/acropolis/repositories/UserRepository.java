package com.acropolis.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acropolis.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

Optional<Users> findByEmailAndPassword(String email, String password);
	
	List<Users> findByRole(String role);

	Optional<Users> findByEmail(String username);

}
