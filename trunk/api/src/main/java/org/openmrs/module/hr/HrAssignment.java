package org.openmrs.module.hr;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.Location;


public class HrAssignment extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int assignmentId;
     private HrPostHistory hrPostHistory;
     private HrStaff supervisor;
     private Location location;
     private String assignment;
     private String timeBasis;
     private Concept workSchedule;
     private Date startDate;
     private Date endDate;
     private Concept endReason;
     private String endReasonOther;
     private String note;;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrAssignment() {
    }

	/** minimal constructor */
    public HrAssignment(int assignmentId, HrPostHistory hrPostHistory, Location location,String uuid) {
        this.assignmentId = assignmentId;
        this.hrPostHistory = hrPostHistory;
        this.location=location;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrAssignment(int assignmentId, HrPostHistory hrPostHistory, HrStaff supervisor, Location location, String assignment, String timeBasis, Concept workSchedule, Date startDate, Date endDate, Concept endReason, String endReasonOther, String note, String uuid) {
        this.assignmentId = assignmentId;
        this.hrPostHistory = hrPostHistory;
        this.location=location;
        this.workSchedule=workSchedule;
        this.supervisor = supervisor;
        this.assignment = assignment;
        this.timeBasis = timeBasis;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endReason = endReason;
        this.endReasonOther = endReasonOther;
        this.note = note;
        this.uuid = uuid;
    }
    

   
    // Property accessors

    public int getAssignmentId() {
        return this.assignmentId;
    }
    
    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public HrPostHistory getHrPostHistory() {
        return this.hrPostHistory;
    }
    
    public void setHrPostHistory(HrPostHistory hrPostHistory) {
        this.hrPostHistory = hrPostHistory;
    }

    public HrStaff getSupervisor() {
        return this.supervisor;
    }
    
    public void setSupervisor(HrStaff supervisor) {
        this.supervisor = supervisor;
    }

    public String getAssignment() {
        return this.assignment;
    }
    
    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
    public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Concept getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(Concept workSchedule) {
		this.workSchedule = workSchedule;
	}

    public String getTimeBasis() {
        return this.timeBasis;
    }
    
    public void setTimeBasis(String timeBasis) {
        this.timeBasis = timeBasis;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Concept getEndReason() {
        return this.endReason;
    }
    
    public void setEndReason(Concept endReason) {
        this.endReason = endReason;
    }

    public String getEndReasonOther() {
        return this.endReasonOther;
    }
    
    public void setEndReasonOther(String endReasonOther) {
        this.endReasonOther = endReasonOther;
    }

    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getAssignmentId();
	}

	public void setId(Integer id) {
		setAssignmentId(id);
		
	}
   








}
