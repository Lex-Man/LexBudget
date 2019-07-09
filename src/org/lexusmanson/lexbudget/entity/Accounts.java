/**
 * This file is used to represent the database table account.
 * 
 * The database table take the following form:
 * id int : represents the accounts id number
 * organisation VARCHAR(50) : represents the name of the organisation the account is with.
 * function VARCHAR(50) : represents the purpose of the account, for example saving.
 * current_balance DECIMAL(10,2) : represents the current budget of the savings account. 
 * 
 * The table is in a one to many relationship with the transactions table.
 * 
 */

package org.lexusmanson.lexbudget.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name="accounts")
public class Accounts {

	
	/**
	 * integer is used to store data from the id column in the accounts table.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/**
	 * String is used to store data from the organisation column in the accounts table.
	 */
	@Column(name="organisation")
	private String organisation;
	
	/**
	 * String is used to store data from the function column in the accounts table.
	 */
	@Column(name="function")
	private String function;
	
	/**
	 * double value is used to store data from the current_balacne column in the accounts table.
	 */
	@Column(name="current_balance")
	private double currentBalance;
	
	/**
	 * List is used to store all of Transactions instances that are related to the Accounts instance.  
	 * Each Transaction instance represents a record from the transactions table.
	 */
	@OneToMany(mappedBy="accountsId", cascade= {CascadeType.ALL}, fetch=FetchType.LAZY)
	//@JsonManagedReference
	@JsonIgnore
	private List<Transactions> transactions;
	
	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	/**
	 * Creates a new instance the accounts class with a empty list of transactions. 
	 */
	public Accounts() {
		transactions = new ArrayList<Transactions>();
	}
	
	/**
	 * Creates a Accounts instance with the specified organisation, function, currentBalance and transaction valibles set.
	 */
	public Accounts(String organisation, String function, double currentBalance, ArrayList<Transactions> transaction) {
		
		this.organisation = organisation;
		this.function = function;
		this.currentBalance = currentBalance;
		this.transactions = transactions;
	}

	/**
	 * Returns the current id variable.
	 * 
	 * @return id - the id of the current account instance.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id of current instance.
	 * 
	 * @param - the id of the current account instance.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the current organisation who provide the account.
	 * 
	 * @return organisation - the name of the current organisation.
	 */
	public String getOrganisation() {
		return organisation;
	}

	/**
	 * Sets the name of the organisation who provide the account.
	 * 
	 * @param - the name of the organisation.
	 */
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	/**
	 * Returns the current function of the account.
	 * 
	 * @return - returns a description of the accounts function.
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * Sets the function of the account.
	 * 
	 * @param function - the function of the account.
	 */
	public void setFunction(String function) {
		this.function = function;
	}


	/**
	 * Returns the current account balance.
	 * 
	 * @return - returns the current balance of the account.
	 */
	public double getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * Sets the current balance of the account to the double currentBalance.
	 * 
	 * @param currentBalance - the new balance of the account.
	 */
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**
	 * Returns a string consisting of accounts followed by all the instances parameters in square parameters.
	 * 
	 * @return - returns a description of the current instances.
	 */
	@Override
	public String toString() {
		return "accounts [id=" + id + ", organisation=" + organisation + ", function=" + function + ", startingBalance="
				 + ", currentBalance=" + currentBalance + "]";
	}
	
}
