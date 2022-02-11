package org.springframework.samples.petclinic.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
	@Autowired
	private BillService billService;

	@RequestMapping(value="/bills", method=RequestMethod.GET)
	public String findAll() {
		List<Bill> bills = this.billService.findAll();
		String sBills = "";
		for(Bill bill : bills) {
			sBills = sBills + "Id: " + bill.getId() + "\n";
		}
		return sBills;
	}
	
	@RequestMapping(value="/bills/{idBill}", method=RequestMethod.GET)
	public Bill findById(@PathVariable("idBill") Integer id) {
		Bill b = this.billService.findById(id);
		return b;
	}
	
	@RequestMapping(value="/bills/save", method=RequestMethod.POST)
	public Bill saveBill(@RequestBody Bill b) {
		if(b != null)
			return billService.save(b);
		return null;
	}

	@RequestMapping(value="/bills/delete/{idBill}", method=RequestMethod.DELETE) 
	public ResponseEntity<Bill> deleteBill(@PathVariable("idBill") Integer id) {
		Bill fromDB = this.billService.findById(id);
		if(fromDB != null) {
			this.billService.deleteBill(fromDB);
			return ResponseEntity.status(HttpStatus.OK).body(fromDB);			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);	
	}

	@RequestMapping(value="/bills/delete", method=RequestMethod.DELETE) 
	public void deleteAllBills() {
		this.billService.deleteAll();			
	}

}

