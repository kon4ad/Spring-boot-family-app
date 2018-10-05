package com.konrad.loch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	

}
