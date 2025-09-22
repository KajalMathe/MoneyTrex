package com.acropolis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Users;

@Repository
public interface ExpenseLimiterRepository extends JpaRepository<ExpenseLimiters, Integer> {

	Optional<ExpenseLimiters> findByUser(Users user);

	

}
