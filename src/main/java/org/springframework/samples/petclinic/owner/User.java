package org.springframework.samples.petclinic.owner;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

@Entity
@Table(name = "account_users")
public class User extends BaseEntity{

    @Column(name = "username")
    @NotEmpty
    private String username;
    

    @Column(name = "password")
    @NotEmpty
    private String password;
    
    @Column(name = "time_account")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date timeAccount;
    
   
    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;

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


	public Date getTimeAccount() {
		return timeAccount;
	}


	public void setTimeAccount(Date timeAccount) {
		this.timeAccount = timeAccount;
	}


	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}
    
    
    
}
