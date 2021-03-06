package com.freetests4u.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	private String id ;
	
	@NotEmpty(message="username required")
	@NotNull(message="username required")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message="email required")
	@NotNull(message="email required")
	@Column(name="email")
	private String email;
	
	@NotNull(message="socialId or password required")
	@NotEmpty(message="socialId or password required")
	@Column(name="socialid")
	private String socialId;
	
	@Column(name="registrationdate")
	private Timestamp registrationDate; 
	
	@Column(name="lastlogindate")
	private Timestamp lastloginDate;
	
	@NotNull(message="loginType required")
	@NotEmpty(message="loginType required")
	@Column(name="logintype")
	private String loginType;
	
	@Column(name="deleted")
	private boolean isDeleted;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getLastloginDate() {
		return lastloginDate;
	}

	public void setLastloginDate(Timestamp lastloginDate) {
		this.lastloginDate = lastloginDate;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
