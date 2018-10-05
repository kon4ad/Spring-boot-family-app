package com.konrad.loch.repositories;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;

public interface FamilyRepository {
	long createFamily();
	
	void addFatherTofamily(Father father, int familyId);
	
	void addChildTofamily(Child child, int familyId);
	//List<Integer> searchChild(String firstName, String secondName, String pesel, String sex, String date); //return ids of found childs
	List<Integer> searchChild(Map<String,String> paramMaps); //return ids of found childs
	 
	List<Integer> searchChild(int familyID);
	
	Set<Family> readFamily(List<Integer> childIdList);
	
	Family readFamily(int familyId);
	
	Child readChild(int childId);
	
	Father readFather(int fatherId);
}

