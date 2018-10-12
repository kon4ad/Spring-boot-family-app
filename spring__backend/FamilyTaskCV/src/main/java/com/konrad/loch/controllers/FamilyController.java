package com.konrad.loch.controllers;



import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;
import com.konrad.loch.exceptions.SaveOperationException;
import com.konrad.loch.services.FamilyService;


@RestController
public class FamilyController {
	
	private FamilyService familyService;
	
	@Autowired
	public FamilyController(FamilyService familyService){
		this.familyService = familyService;
	}
	
	@GetMapping("/create/family")
	public ResponseEntity<Integer> createFamily() throws SaveOperationException{
		return ResponseEntity.status(HttpStatus.CREATED).body(this.familyService.createFamily());
	}
	
	@PostMapping("/add/child")
	public ResponseEntity<Integer> addChildToFamily(@RequestBody Child child, 
			@RequestParam("familyid") String familyId) throws NumberFormatException, SaveOperationException{
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.addChildTofamily(child, Integer.parseInt(familyId)));
	}
	
	@PostMapping("/add/father")
	public ResponseEntity<Integer> addFatherToFamily(@RequestBody Father child, 
			@RequestParam("familyid") String familyId) throws NumberFormatException, SaveOperationException{
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.addFatherTofamily(child, Integer.parseInt(familyId)));
	}
	
	@GetMapping("/read/family")
	public ResponseEntity<Family> readFamily(@RequestParam("childId") String childId){
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readFamily(Integer.parseInt(childId), true));
	}
	
	@GetMapping("/read/family/{id}")
	public ResponseEntity<Family> readFamilyById(@PathVariable("id") String id){
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readFamily(Integer.parseInt(id), false));
	}
	
	@GetMapping("/search/child")
	public ResponseEntity<List<Integer>> searchChild(@RequestParam Map<String,String> paramMap){
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.searchChild(paramMap));
	}
	
	@GetMapping("/read/father/{id}")
	public ResponseEntity<Father> readFather(@PathVariable("id") String fatherId){
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readFather(Integer.parseInt(fatherId)));
	}
	
	@GetMapping("/read/child/{id}")
	public ResponseEntity<Child> readChild(@PathVariable("id") String childId){
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readChild(Integer.parseInt(childId)));
	}
	

}
