package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;



public class HrStaff extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int staffId;
     private Concept staffStatus;
     private Date initialHireDate;
     private String uuid;
     private Set<HrAssignment> hrAssignments = new HashSet<HrAssignment>(0);
     private Set<HrStaffNote> hrStaffNotes = new HashSet<HrStaffNote>(0);
     private Set<HrStaffAttribute> hrStaffAttributes = new HashSet<HrStaffAttribute>(0);
     private Set<HrEducation> hrEducations = new HashSet<HrEducation>(0);
     private Set<HrEvaluation> hrEvaluations = new HashSet<HrEvaluation>(0);
     private Set<HrStaffCert> hrStaffCerts = new HashSet<HrStaffCert>(0);
     private Set<HrPostHistory> hrPostHistories = new HashSet<HrPostHistory>(0);


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
    public HrStaff(int staffId, Concept staffStatus, Date initialHireDate,String uuid, Set<HrAssignment> hrAssignments,Set<HrStaffNote> hrStaffNotes, Set<HrStaffAttribute> hrStaffAttributes, Set<HrEducation> hrEducations, Set<HrEvaluation> hrEvaluations, Set<HrStaffCert> hrStaffCerts, Set<HrPostHistory> hrPostHistories) {
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

    public Set<HrAssignment> getHrAssignments() {
        return this.hrAssignments;
    }
    
    public void setHrAssignments(Set<HrAssignment> hrAssignments) {
        this.hrAssignments = hrAssignments;
    }

     public Set<HrStaffNote> getHrStaffNotes() {
        return this.hrStaffNotes;
    }
    
    public void setHrStaffNotes(Set<HrStaffNote> hrStaffNotes) {
        this.hrStaffNotes = hrStaffNotes;
    }

    public Set<HrStaffAttribute> getHrStaffAttributes() {
        return this.hrStaffAttributes;
    }
    
    public void setHrStaffAttributes(Set<HrStaffAttribute> hrStaffAttributes) {
        this.hrStaffAttributes = hrStaffAttributes;
    }

    public Set<HrEducation> getHrEducations() {
        return this.hrEducations;
    }
    
    public void setHrEducations(Set<HrEducation> hrEducations) {
        this.hrEducations = hrEducations;
    }

    public Set<HrEvaluation> getHrEvaluations() {
        return this.hrEvaluations;
    }
    
    public void setHrEvaluations(Set<HrEvaluation> hrEvaluations) {
        this.hrEvaluations = hrEvaluations;
    }

    public Set<HrStaffCert> getHrStaffCerts() {
        return this.hrStaffCerts;
    }
    
    public void setHrStaffCerts(Set<HrStaffCert> hrStaffCerts) {
        this.hrStaffCerts = hrStaffCerts;
    }

    public Set<HrPostHistory> getHrPostHistories() {
        return this.hrPostHistories;
    }
    
    public void setHrPostHistories(Set<HrPostHistory> hrPostHistories) {
        this.hrPostHistories = hrPostHistories;
    }

	public Integer getId() {
		return getStaffId();
	}

	public void setId(Integer id) {
		setStaffId(id);
	}
}
