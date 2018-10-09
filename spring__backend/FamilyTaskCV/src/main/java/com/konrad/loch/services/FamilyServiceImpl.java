package com.konrad.loch.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.konrad.loch.domains.Child;
import com.konrad.loch.domains.Family;
import com.konrad.loch.domains.Father;
import com.konrad.loch.exceptions.SaveOperationException;
import com.konrad.loch.repositories.FamilyRepository;

@Service
@Transactional
public class FamilyServiceImpl implements FamilyService {

	@Autowired
	private FamilyRepository familyRepository;

	@Override
	public int createFamily() throws SaveOperationException {
		return this.familyRepository.saveNewFamily();
	}

	@Override
	public int addFatherTofamily(Father father, int familyId) throws SaveOperationException  {
		int fatherId = this.familyRepository.saveFather(father);
		if (this.familyRepository.updateFatherTofamily(fatherId, familyId)) {
			return fatherId;
		} else {
			return -1;
		}
	}

	@Override
	public int addChildTofamily(Child child, int familyId) throws SaveOperationException {
		int childId = this.familyRepository.saveChild(child);
		if (this.familyRepository.updateChildTofamily(childId, familyId)) {
			return childId;
		} else {
			return -1;
		}
	}

	@Override
	public List<Integer> searchChild(Map<String, String> paramMaps) {
		return this.familyRepository.searchChildByMapParams(paramMaps);
	}

	@Override
	public Family readFamily(int ide, boolean fromChildId) {
		int familyId = ide;
		if(fromChildId){
			familyId = this.familyRepository.readFamilyIdByChildId(ide);
			if(familyId == -1){
				return null;
			}			
		}

		List<Child> childrenList = new ArrayList<>();
		List<Integer> childrenIds = this.searchChild(familyId);
		for(int id : childrenIds){
			childrenList.add(this.readChild(id));
		}
		Father father = this.readFather(this.familyRepository.readFatherIdByFamilyId(familyId));
		
		if(father == null){
			return null;
		}
		
		Family family = new Family();
		family.setId(familyId);
		family.setChild(childrenList);
		family.setFather(father);
		return family;
	}
	
	@Override
	public Child readChild(int childId) {
		return this.familyRepository.readChild(childId);
	}

	@Override
	public Father readFather(int fatherId) {
		return this.familyRepository.readFather(fatherId);
	}

	@Override
	public List<Integer> searchChild(int familyId) {
		return this.familyRepository.searchChildByFamilyId(familyId);
	}

}
