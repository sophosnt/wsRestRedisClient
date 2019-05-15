package com.sophos.poc.wsrestredisclient.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sophos.poc.wsrestredisclient.dto.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	Optional<Account> findById(String id);
}
