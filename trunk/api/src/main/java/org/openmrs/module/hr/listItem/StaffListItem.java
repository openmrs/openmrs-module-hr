package org.openmrs.module.hr.listItem;

import org.openmrs.Person;
import org.openmrs.module.hr.HrStaff;

public class StaffListItem {

	private Person person;
	private HrStaff staff;
	private String locationName="";
	private String jobTitle="";
	public StaffListItem(){
		
	}
	public StaffListItem(Person person, HrStaff staff, String locationName,String jobTitle) {
		this.person = person;
		this.staff = staff;
		this.locationName = locationName;
		this.jobTitle = jobTitle;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public HrStaff getStaff() {
		return staff;
	}
	public void setStaff(HrStaff staff) {
		this.staff = staff;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

}
