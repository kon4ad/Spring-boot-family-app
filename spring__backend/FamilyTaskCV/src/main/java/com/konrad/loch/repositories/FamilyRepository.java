package com.konrad.loch.repositories;

import java.util.List;
import java.util.Map;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;
import com.konrad.loch.exceptions.SaveOperationException;

public interface FamilyRepository {
	int saveNewFamily() throws SaveOperationException;
	
	int saveFather(Father father) throws SaveOperationException;
	
	int saveChild(Child child) throws SaveOperationException;
	
	Family readFamilyById(int familyId);
	
	Child readChild(int childId);
	
	Father readFather(int fatherId);
	
	boolean updateFatherTofamily(int fatherId, int familyId);
	
	boolean updateChildTofamily(int newChildId, int familyId);

	List<Integer> searchChildByMapParams(Map<String,String> paramMaps); //return ids of found childs
	 
	List<Integer> searchChildByFamilyId(int familyID);
	
	int readFamilyIdByChildId(int childId);
	
	int readFatherIdByFamilyId(int familyId);
	
}

