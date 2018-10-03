package com.konrad.loch.domains;

import java.util.ArrayList;
import java.util.List;

public class Family {

	private int id;
	private Father father;
	private List<Child> child;
	
	public Family(){
		this.child = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Father getFather() {
		return father;
	}

	public void setFather(Father father) {
		this.father = father;
	}

	public List<Child> getChild() {
		return child;
	}

	public void setChild(List<Child> child) {
		this.child = child;
	}
	
	
}
