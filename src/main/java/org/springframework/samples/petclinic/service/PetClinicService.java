package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.bill.Bill;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.owner.PetType;
import org.springframework.samples.petclinic.vet.SpecialityRepository;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class PetClinicService {

	//Pet
	
	@Autowired
	private PetRepository petRepository;
	
	public List<PetType> findPetTypes() {
		return this.petRepository.findPetTypes();
	}
		
	
	//Visit
	
	@Autowired
	private VisitRepository visitRepository;
	
	public List<Pet> findByBirthDate(int year){
		return visitRepository.findByBirthDate(year);
	}	

	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}
	
	public List<Visit> findByPetId(Integer petId){
		return visitRepository.findByPetId(petId);
	}
	
	public List<Visit> findTop4ByOrderByDateAsc(){
		return visitRepository.findTop4ByOrderByDateAsc();
	}
	

	
	//Vet
	
	@Autowired
	private VetRepository vetRepository;
	
	
	//Specialty
	
	@Autowired
	private SpecialityRepository specRepository;
	
	
	//Owner
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	public Owner findById(int id) {
		return this.ownerRepository.findById(id);
	}
	
	public Collection<Owner> findByLastName(String lastName){
		return ownerRepository.findByLastName(lastName);
	}
	
	public Collection<Owner> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName){
		return ownerRepository.findByFirstNameContainingOrLastNameContaining(firstName, lastName);
	} 
	
	public Collection<Owner> searchOwner(String query){
		return ownerRepository.searchOwner(query);
	}
	
	public Collection<Owner> findByOrderByLastName(){
		return ownerRepository.findByOrderByLastName();
	}
	
	public Collection<Owner> findAll(){
		return ownerRepository.findAll();
	}

}
