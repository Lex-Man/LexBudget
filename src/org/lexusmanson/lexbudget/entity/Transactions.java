/**
 * Represents the transactions table transactions in the database.  
 * 
 * The database table takes the following form: 
 * id int: represents the transactions id number.
 * payee varchar(50): who the transaction is for.
 * description varchar(50): A description of the payment.
 * reference varchar(50): A reference description for the payment.
 * date DATE: the date when the transaction was added.
 * amount DECIMAL(10,2): the amount the transaction is for
 * account_id int: The id of the account that the transaction
 * balance DECIMAL (10,2): the current account balance after the transaction has been completed.
 *
 * The account_id element represents a many to one relationship with the Accounts table represented by accounts entities. 
 *
 */

package org.lexusmanson.lexbudget.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name="transactions")
public class Transactions {

	/**
	 * Represents the transactions id.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	/**
	 * represents the names of the payee the transaction is related to.
	 */
	@Column(name="payee")
	public String payee;
	
	
	/**
	 * stores a description of the transactions purpose.
	 */
	@Column(name="description")
	public String description;
	
	/**
	 * stores the reference information for the transaction.
	 */
	@Column(name="reference")
	public String reference;
	
	/**
	 * stores the category of the transactions.
	 */
	@Column(name="category")
	public String category;
	
	/**
	 * stores the date that the transaction was made.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="date")
	@Valid
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate date;
	
	@Transient
	public String dateAsString;
	
	public String getDateAsString() {
		return dateAsString;
	}

	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}

	/**
	 * Stores the amount that the transaction is for.
	 */
	@Column(name="amount")
	public double amount;
	
	/**
	 * The current balance of the associated account after the transaction has been applyied.
	 */
	@Column(name="balance")
	public double balance;
	
	
	/**
	 * Returns the current balance of the associated account after the transaction has been applied.
	 * @return - the current balance of the account after the transaction has been applied.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Set the balance of the transactions.
	 * @param balance - sets the balance of the transaction.
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Stores the related account object as defined by the account_id coloumn in the transaction table.
	 */
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="accounts_id")
	@JsonBackReference
	public Accounts accountsId;
	
	
	/**
	 * Creates an instance of the transaction class without setting any of the variables.
	 */
	public Transactions() {
		//date = LocalDate.parse("dd/MM/YYYY");
	}
	
	/**
	 * Creates a transaction instance setting each variable in the 
	 * @param id
	 * @param payee
	 * @param description
	 * @param reference
	 * @param category
	 * @param date
	 * @param amount
	 * @param accounts_id
	 * @param balance
	 */
	public Transactions(int id, String payee, String description, String reference,
						String category, LocalDate date, double amount, Accounts accounts_id
						, double balance) {
		
		this.id = id;
		this.payee = payee;
		this.description = description;
		this.reference = reference;
		this.category = category;
		this.date = date;
		this.amount = amount;
		this.accountsId = accounts_id;
		this.balance = balance;
	}

	/**
	 * Returns the id of the current transaction
	 * 
	 * @return - the current transaction id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the account that is related to this transaction.
	 * 
	 * This is defined by the many to on relationship in the database.  The realtionship is defined by the account_id column.
	 * 
	 * @return - the related account.
	 */
	public Accounts getAccountsId() {
		return accountsId;
	}

	
	/**
	 * Sets the account that will represent the many to one relationship represented by the field accounts_id in the transactions table. 
	 * 
	 * @param accounts_id
	 */
	public void setAccountsId(Accounts accounts_id) {
		this.accountsId = accounts_id;
	}

	
	/**
	 * Sets the transactions id which represents the primary key for the transactions table.
	 * 
	 * @param id - the id of the transactions.
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * Returns the Payee of the transactions.
	 * 
	 * @return - the payee who was involved in the transaction.
	 */
	public String getPayee() {
		return payee;
	}

	/**
	 * Sets the payee who is involved in the transactions.
	 * 
	 * @param payee - the name of the payee involved in the transaction.
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	
	/**
	 * Gets a description of the transactions
	 * 
	 * @return - a string describing the transaction
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the transactions.
	 * @param description - a description of the transaction.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * Returns a reference for the transaction.
	 * 
	 * @return - a reference for the transaction.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference for the transaction.
	 * 
	 * @param reference - a reference for the transaction.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Returns a category for the transaction.
	 * 
	 * @return - a category for the transaction.
	 */
	public String getCategory() {
		return category;
	}

	
	/**
	 * Sets the category for the transaction.
	 * 
	 * @param category - a category of the transaction.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Returns the date the transaction was made on.
	 * 
	 * @return - the date of the transaction.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date of the transaction
	 * 
	 * @param date - the date of the transaction.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	/**
	 * The amount the transaction is for.
	 *  
	 * @return - the amount the balance is for.
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * The amount of the transaction if for.
	 * 
	 * @param amount - the amount of the transaction.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	/**
	 * Returns a string consisting of transactions followed by all of the instances parameters in square brackets.
	 *
	 *	@return - a description of the current transaction instance.
	 */
	@Override
	public String toString() {
		return "transactions [id=" + id + ", payee=" + payee + ", description=" + description + ", reference="
				+ reference + ", category=" + category + ", date=" + date + ", amount=" + amount + ", accounts_id="
				+ accountsId + "]";
	}
	
	
}
