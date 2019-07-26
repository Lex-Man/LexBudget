package org.lexusmanson.lexbudget.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authorities implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Authorities() {
		
	}
	
	public Authorities(User username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}

	@Id
	//@Column(name="username")
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="username")
	private User username;
	
	@Id
	@Column(name="authority")
	private String authority;
	
	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
