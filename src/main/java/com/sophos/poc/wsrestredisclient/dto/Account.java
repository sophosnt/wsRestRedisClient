package com.sophos.poc.wsrestredisclient.dto;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.validation.annotation.Validated;

@RedisHash("Account")
@Validated
public class Account {

	@Id
	public String id;
	public Integer account_id;
	public String account_type;
	public Integer card_id;
	public Integer pin;
	public BigDecimal account_bal;
	public String currency;
	
	// Constructors
	public Account() {
	}

	public Account(String id, Integer account_id, String account_type, Integer card_id, int pin, BigDecimal bal, String currency) {
		this.id = id;
		this.account_id = account_id;
		this.account_type = account_type;
		this.card_id = card_id;
		this.pin = pin;
		this.account_bal = bal;
		this.currency = currency;
	}

	// ObjectId needs to be converted to string
	public String getId() {
		return id;
	}

	public void set_id(String id) {
		this.id = id;
	}

	public Integer getaccount_id() {
		return account_id;
	}

	public void setaccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public String getaccount_type() {
		return account_type;
	}

	public void setaccount_type(String account_type) {
		this.account_type = account_type;
	}

	public Integer getcard_id() {
		return card_id;
	}

	public void setcard_id(Integer card_id) {
		this.card_id = card_id;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public BigDecimal getAccount_bal() {
		return account_bal;
	}

	public void setAccount_bal(BigDecimal account_bal) {
		this.account_bal = account_bal;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "{\"ACCOUNT_ID\":" + account_id + ", \"ACCOUNT_TYPE\":" + "\""+ account_type + "\""+ ", \"CARD_ID\":" + card_id
				+ ", \"PIN\":" + pin + ", \"ACCOUNT_BAL\":" + account_bal + ", \"CURRENCY\":" + "\""+ currency + "\""+ "}";
	}
	

}
