package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.Person;


public class HrStaff extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     private int staffId;
     private Concept staffStatus;
     private Date initialHireDate;
     private String uuid;
     private Set hrAssignments = new HashSet(0);
     private Set hrStaffNotes = new HashSet(0);
     private Set hrStaffAttributes = new HashSet(0);
     private Set hrEducations = new HashSet(0);
     private Set hrEvaluations = new HashSet(0);
     private Set hrStaffCerts = new HashSet(0);
     private Set hrPostHistories = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrStaff() {
    }

	/** minimal constructor */
    public HrStaff(int staffId, String uuid) {
        this.staffId = staffId;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaff(int staffId, Concept staffStatus, Date initialHireDate,String uuid, Set hrAssignments, Set hrStaffNotes, Set hrStaffAttributes, Set hrEducations, Set hrEvaluations, Set hrStaffCerts, Set hrPostHistories) {
        this.staffId = staffId;
        this.staffStatus = staffStatus;
        this.initialHireDate = initialHireDate;
        this.uuid = uuid;
        this.hrAssignments = hrAssignments;
        this.hrStaffNotes = hrStaffNotes;
        this.hrStaffAttributes = hrStaffAttributes;
        this.hrEducations = hrEducations;
        this.hrEvaluations = hrEvaluations;
        this.hrStaffCerts = hrStaffCerts;
        this.hrPostHistories = hrPostHistories;
    }
    

   
    // Property accessors

    public int getStaffId() {
        return this.staffId;
    }
    
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    
    public Concept getStaffStatus() {
        return this.staffStatus;
    }
    
    public void setStaffStatus(Concept staffStatus) {
        this.staffStatus = staffStatus;
    }

    public Date getInitialHireDate() {
        return this.initialHireDate;
    }
    
    public void setInitialHireDate(Date initialHireDate) {
        this.initialHireDate = initialHireDate;
    }

    
    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set getHrAssignments() {
        return this.hrAssignments;
    }
    
    public void setHrAssignments(Set hrAssignments) {
        this.hrAssignments = hrAssignments;
    }

     public Set getHrStaffNotes() {
        return this.hrStaffNotes;
    }
    
    public void setHrStaffNotes(Set hrStaffNotes) {
        this.hrStaffNotes = hrStaffNotes;
    }

    public Set getHrStaffAttributes() {
        return this.hrStaffAttributes;
    }
    
    public void setHrStaffAttributes(Set hrStaffAttributes) {
        this.hrStaffAttributes = hrStaffAttributes;
    }

    public Set getHrEducations() {
        return this.hrEducations;
    }
    
    public void setHrEducations(Set hrEducations) {
        this.hrEducations = hrEducations;
    }

    public Set getHrEvaluations() {
        return this.hrEvaluations;
    }
    
    public void setHrEvaluations(Set hrEvaluations) {
        this.hrEvaluations = hrEvaluations;
    }

    public Set getHrStaffCerts() {
        return this.hrStaffCerts;
    }
    
    public void setHrStaffCerts(Set hrStaffCerts) {
        this.hrStaffCerts = hrStaffCerts;
    }

    public Set getHrPostHistories() {
        return this.hrPostHistories;
    }
    
    public void setHrPostHistories(Set hrPostHistories) {
        this.hrPostHistories = hrPostHistories;
    }

	public Integer getId() {
		return getStaffId();
	}

	public void setId(Integer id) {
		setStaffId(id);
	}
}
