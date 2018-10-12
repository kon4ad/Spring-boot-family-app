package com.konrad.loch.controllers;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(FamilyController.class);
	
	@Autowired
	public FamilyController(FamilyService familyService){
		this.familyService = familyService;
	}
	
	@GetMapping("/create/family")
	public ResponseEntity<Integer> createFamily() throws Exception{
		logger.info("createFamily() invoked");
		return ResponseEntity.status(HttpStatus.CREATED).body(this.familyService.createFamily());
	}
	
	@PostMapping("/add/child")
	public ResponseEntity<Integer> addChildToFamily(@RequestBody Child child, 
			@RequestParam("familyid") String familyId) throws NumberFormatException, SaveOperationException{
		logger.info("addChildToFamily() invoked, body: {}, param: {}", child.toString(), familyId);
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.addChildTofamily(child, Integer.parseInt(familyId)));
	}
	
	@PostMapping("/add/father")
	public ResponseEntity<Integer> addFatherToFamily(@RequestBody Father father, 
			@RequestParam("familyid") String familyId) throws NumberFormatException, SaveOperationException{
		logger.info("addFatherToFamilyy() invoked, body: {}, param: {}", father.toString(), familyId);
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.addFatherTofamily(father, Integer.parseInt(familyId)));
	}
	
	@GetMapping("/read/family")
	public ResponseEntity<Family> readFamily(@RequestParam("childId") String childId){
		logger.info("readFamily() invoked, child - param: {}", childId);
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readFamily(Integer.parseInt(childId), true));
	}
	
	@GetMapping("/read/family/{id}")
	public ResponseEntity<Family> readFamilyById(@PathVariable("id") String id){
		logger.info("readFamily() invoked, id - param: {}", id);
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readFamily(Integer.parseInt(id), false));
	}
	
	@GetMapping("/search/child")
	public ResponseEntity<List<Integer>> searchChild(@RequestParam Map<String,String> paramMap){
		logger.info("searchChild() invoked, param: {}", paramMap.toString());
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.searchChild(paramMap));
	}
	
	@GetMapping("/read/father/{id}")
	public ResponseEntity<Father> readFather(@PathVariable("id") String fatherId){
		logger.info("readFather() invoked, param: {}", fatherId);
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readFather(Integer.parseInt(fatherId)));
	}
	
	@GetMapping("/read/child/{id}")
	public ResponseEntity<Child> Child(@PathVariable("id") String childId){
		logger.info("readChild() invoked, param: {}", childId);
		return ResponseEntity.status(HttpStatus.OK).body(this.familyService.readChild(Integer.parseInt(childId)));
	}
	

}
