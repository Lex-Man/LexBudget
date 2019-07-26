package org.lexusmanson.lexbudget.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	public User() {
		authorities = new ArrayList<>();
	}
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@OneToMany(mappedBy="username", cascade= {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Authorities> authorities;
	
	public String getUsername() {
		
		return username;
	}
	
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	public Boolean getEnabled() {
		
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		
		this.enabled = enabled;
	}


	public List<Authorities> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}


	public void addAuthority(Authorities authority) {
		authorities.add(authority);
		
	}
	
	
	
	
}
