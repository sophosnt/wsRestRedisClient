package com.sophos.poc.wsrestredisclient.controller;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophos.poc.wsrestredisclient.dto.Account;
import com.sophos.poc.wsrestredisclient.model.AccountType;
import com.sophos.poc.wsrestredisclient.model.ConsultaSaldoReq;
import com.sophos.poc.wsrestredisclient.model.ConsultaSaldoRes;
import com.sophos.poc.wsrestredisclient.model.StatusType;
import com.sophos.poc.wsrestredisclient.repositories.AccountRepository;


@RestController
@RequestMapping("/accounts")
public class AccountsRestController {
	@Autowired
	private AccountRepository repository;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Account> getAllAcct() {
		return (List<Account>) repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ConsultaSaldoRes> getAcctById(@PathVariable("id") String id) {
		try {
			ConsultaSaldoRes rs = new ConsultaSaldoRes();
			rs.setId_trn(UUID.randomUUID().toString());
			rs.setAuth_code(UUID.randomUUID().toString());
			rs.setServer_date(Calendar.getInstance().getTime());
			rs.setDate(Calendar.getInstance().getTime());
			StatusType status = new StatusType();
			status.setStatus_code(HttpStatus.OK.toString());
			status.setStatus_desc(HttpStatus.OK.name());
			status.setStatus_info(HttpStatus.OK.getReasonPhrase());
			rs.setStatus(status);
			Optional<Account> dbAccount = repository.findById(id);
			ObjectMapper mapper = new ObjectMapper();
			AccountType account  = mapper.readValue(new StringReader(dbAccount.get().toString()), AccountType.class);
			rs.setAccount(account);			
			return new ResponseEntity<ConsultaSaldoRes>(rs, HttpStatus.OK);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyAcctById(@PathVariable("id") String id, @Valid @RequestBody AccountType acctRq) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Account acct;
			acct = mapper.readValue(new StringReader(acctRq.toString()), Account.class);
			acct.set_id(id);
			repository.save(acct);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<AccountType> createAcct(@Valid @RequestBody ConsultaSaldoReq rq) {
		try {
			Account acct = new Account(UUID.randomUUID().toString(), rq.getAccount_id(), rq.getAccount_type(), rq.getCard_id(), rq.getPin(), new BigDecimal(0), "COP");
			repository.save(acct);
			ObjectMapper mapper = new ObjectMapper();
			AccountType acctRs;
			acctRs = mapper.readValue(acct.toString(),AccountType.class);
			return new ResponseEntity<AccountType>(acctRs,HttpStatus.OK);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
