package com.konrad.loch.services;

import java.util.List;
import java.util.Map;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;
import com.konrad.loch.exceptions.SaveOperationException;

public interface FamilyService {

	int createFamily() throws SaveOperationException;
	
	int addFatherTofamily(Father father, int familyId) throws SaveOperationException;
	
	int addChildTofamily(Child child, int familyId) throws SaveOperationException;

	List<Integer> searchChild(Map<String,String> paramMaps);
	
	List<Integer> searchChild(int familyId);
	
	Family readFamily(int childId);

	Child readChild(int childId);
	
	Father readFather(int fatherId);
}
