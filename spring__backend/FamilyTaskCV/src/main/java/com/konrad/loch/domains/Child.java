package com.konrad.loch.domains;

import java.sql.Date;

public class Child {

	private int id;
	private String firstName;
	private String secondName;
	private String PESEL;
	private String sex;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getPESEL() {
		return PESEL;
	}
	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "Child [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", PESEL=" + PESEL
				+ ", sex=" + sex + "]";
	}
	
	
}
