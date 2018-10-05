package com.konrad.loch.controllers;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		return new ResponseEntity<Integer>(this.familyService.createFamily(), HttpStatus.CREATED);
	}
	
	@PostMapping("/add/child")
	public ResponseEntity<Integer> addChildToFamily(@RequestBody Child child, 
			@RequestParam("familyid") String familyId) throws NumberFormatException, SaveOperationException{
		return new ResponseEntity<Integer>(this.familyService.addChildTofamily(child, Integer.parseInt(familyId)), HttpStatus.OK);
	}
	
	@PostMapping("/add/father")
	public ResponseEntity<Integer> addFatherToFamily(@RequestBody Father child, 
			@RequestParam("familyid") String familyId) throws NumberFormatException, SaveOperationException{
		return new ResponseEntity<Integer>(this.familyService.addFatherTofamily(child, Integer.parseInt(familyId)), HttpStatus.OK);
	}
	
	@GetMapping("/read/family")
	public ResponseEntity<Family> readFamily(@RequestParam("childId") String childId){
		return new ResponseEntity<Family>(this.familyService.readFamily(Integer.parseInt(childId)), HttpStatus.OK);
	}
	
	@GetMapping("/search/child")
	public ResponseEntity<List<Integer>> searchChild(@RequestParam Map<String,String> paramMap){
		return new ResponseEntity<List<Integer>>(this.familyService.searchChild(paramMap), HttpStatus.OK);
	}
	
	@GetMapping("/read/father")
	public ResponseEntity<Father> readFather(@RequestParam("fatherId") String fatherId){
		return new ResponseEntity<Father>(this.familyService.readFather(Integer.parseInt(fatherId)), HttpStatus.OK);
	}
	
	@GetMapping("/read/child")
	public ResponseEntity<Child> readChild(@RequestParam("childId") String childId){
		return new ResponseEntity<Child>(this.familyService.readChild(Integer.parseInt(childId)), HttpStatus.OK);
	}
	

}
