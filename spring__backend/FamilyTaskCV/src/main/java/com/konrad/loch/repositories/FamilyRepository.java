package com.konrad.loch.repositories;

import java.util.List;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;

public interface FamilyRepository {
	int createFamily();
	void addFatherTofamily(Father father, int familyId);
	void addChildTofamily(Child child, int familyId);
	List<Integer> searchChild(); //return ids of found childs
	Family readFamily(int id);
	Child readChild(int id);
	Father readFather(int id);
}
