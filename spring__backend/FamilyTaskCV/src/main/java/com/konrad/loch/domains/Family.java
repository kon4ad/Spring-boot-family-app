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

	@Override
	public String toString() {
		return "Family [id=" + id + ", father=" + father + ", child=" + child + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (father == null) {
			if (other.father != null)
				return false;
		} else if (!father.equals(other.father))
			return false;
		return true;
	}
	
	
	
	
}
