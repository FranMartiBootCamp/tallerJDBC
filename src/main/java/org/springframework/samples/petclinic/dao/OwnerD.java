package org.springframework.samples.petclinic.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "owners")
public class OwnerD{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String first_name;

	public OwnerD(String first_Name) {
		super();
		this.first_name = first_Name;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_Name) {
		this.first_name = first_Name;
	}
	
	
}
