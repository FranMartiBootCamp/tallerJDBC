package org.springframework.samples.petclinic.visit;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

public class VisitController {

	@Autowired
	public PetService petService;

	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") int petId, Map<String, Object> model) {
	        Pet pet = this.petService.findById(petId);
	        model.put("pet", pet);
	        Visit visit = new Visit();
	        pet.addVisit(visit);
	        return visit;
	}

	
}
