package com.acropolis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByUser(Users user);

	@Query("select t from Transaction t where month(t.entrydate) = :month AND year(t.entrydate) = :year AND t.user= :user")
	List<Transaction> findByMonthANDYear(Integer month, Integer year, Users user);

	@Query("select t from Transaction t where month(t.entrydate) = :month AND year(t.entrydate) = :year AND t.type= :type AND t.user= :user")
	List<Transaction> findByMonthANDYearANDType(Integer month, Integer year, String type, Users user);
	

}
