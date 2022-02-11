/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.bill.Bill;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.service.PetClinicService;
import org.springframework.samples.petclinic.vet.SpecialityRepository;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */


@SpringBootApplication
public class PetClinicApplication {

	private static final Logger log = LoggerFactory.getLogger(PetClinicApplication.class);
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(PetClinicApplication.class, args);
        
    }
    
//    @Bean
//	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialityRepository specialityRepository) {
//    	return (args) -> {
//			log.info("*****************************************************");
//			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
//			log.info("*****************************************************");
//		
//			log.info("Creamos un objeto Vet");
//			Vet vet = new Vet();
//			vet.setFirstName("Sergio");
//			vet.setLastName("Raposo Vargas");
//		
//			log.info("Persistimos en BBDD");
//			vet = vetRepository.save(vet);
//		
//			log.info("Comprobamos que se ha creado correctamente");
//			Vet vetAux = vetRepository.findOne(vet.getId());
//			log.info(vetAux.toString());
//		
//			log.info("Editamos el objeto y añadimos una Speciality");
//			Specialty s = specialityRepository.findOne(1);
//			vet.addSpecialty(s);
//			vet = vetRepository.save(vet);
//			log.info(vet.toString());
//		
//			log.info("Listamos todos los veterinarios");
//			for(Vet v: vetRepository.findAll()){
//			log.info("Vet: "+v.getFirstName());
//			}
//			
//			log.info("Filtramos por lastName=Raposo Vargas");
//		    for(Vet v: vetRepository.findByLastName("Raposo vargas")) {
//		    	log.info(v.getFirstName());
//		    }
//		    
//		    log.info("Filtramos por lastName=Raposo Vargas y firstName=Sergio");
//		    for(Vet v: vetRepository.findByFirstNameAndLastName("Sergio", "Raposo vargas")) {
//		    	log.info(v.getFirstName());
//		    }
//		    
//		    log.info("Filtramos por Sergio en firstName o en lastName");
//		    for(Vet v: vetRepository.findByFirstNameOrLastName("Sergio", "Sergio")) {
//		    	log.info(v.getFirstName());
//		    }
//		    
//		    log.info("Lista de veterinarios de la especialidad radiology");
//		    for(Vet v: vetRepository.findBySpecialityName("radiology")) {
//		    	log.info(v.getFirstName());
//		    }
//			
//		};
//	}
    
    
//    @Bean
//	public CommandLineRunner demoOwnerRepository(OwnerRepository ownerRepository) {
//    	return (args) -> {
//    		
//    		log.info("*****************************************************");
//    		log.info("BOOTCAMP - Spring y Spring Data - OwnerRepository");
//    		log.info("*****************************************************");
//    		
//	    	log.info("Busqueda de owners por nombre o apellidos");
//		    for(Owner o: ownerRepository.findByFirstNameContainingOrLastNameContaining("George", "Franklin")) {
//		    	log.info(o.getFirstName());
//		    }
//    		
//   		
//    		log.info("Busqueda de owners ordenados por apellido");
//		    for(Owner o: ownerRepository.findByOrderByLastName()) {
//		    	log.info(o.getFirstName());
//		    }
//	    
//    		
//    	};
//    }
    
    
    @Bean
   	public CommandLineRunner demoPetClinicService(PetClinicService petClinicService ) {
    	return (args) -> {
    		
    		log.info("*****************************************************");
    		log.info("BOOTCAMP - Spring y Spring Data - PetClinicService");
    		log.info("*****************************************************");
    		
	    	log.info("Listado de pet ordenados por fecha de nacimiento en 2000");
		    for(Pet p: petClinicService.findByBirthDate(2000)) {
		    	log.info(p.getName() + " " + p.getBirthDate());
		    }
    		
	    
//		    log.info("Creamos un objeto Bill");
//		    Bill bill = new Bill();
//		    bill.setId(1);		    
//		    
//		    Date date = new GregorianCalendar(2022, Calendar.FEBRUARY, 7).getTime();
//		    
//			Visit visit = new Visit();;
//			visit.setPetId(1);
//			visit.setBill(bill);
//			visit.setDate(date);
//			visit.setDescription("Reto3");
//					
//			log.info("Persistimos en BBDD");
//			visit = petClinicService.save(visit);
		
    		
		    Visit visit = new Visit();
		    
		    log.info("Listado de visitas de una pet");
		    for(Visit v: petClinicService.findByPetId(1)) {
		    	log.info("ID: " + v.getId() + " Fecha: " + v.getDate());
		    }
		    
		    log.info("Listado de visitas mas recientes");
		    for(Visit v: petClinicService.findTop4ByOrderByDateAsc()) {
		    	log.info("ID: " + v.getId() + " PetId: " + v.getPetId() + " Fecha: " + v.getDate());
		    }
		    
    	};
    }
   
}
