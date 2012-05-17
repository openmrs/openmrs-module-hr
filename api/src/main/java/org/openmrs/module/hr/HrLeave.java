package org.openmrs.module.hr;

import java.util.Date;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.Concept;

public class HrLeave extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int leaveId;
     private HrPostHistory hrPostHistory;
     private Date startDate;
     private Date endDate;
     private Concept leaveType;
     private String leaveTypeOther;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrLeave() {
    }

	/** minimal constructor */
    public HrLeave(int leaveId, HrPostHistory hrPostHistory, Date startDate, String uuid) {
        this.leaveId = leaveId;
        this.hrPostHistory = hrPostHistory;
        this.startDate = startDate;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrLeave(int leaveId, HrPostHistory hrPostHistory, Date startDate, Date endDate, Concept leaveType, String leaveTypeOther, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short retired, Integer retiredBy, Date dateRetired, String retireReason, String uuid) {
        this.leaveId = leaveId;
        this.hrPostHistory = hrPostHistory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.leaveTypeOther = leaveTypeOther;
        this.uuid = uuid;
    }
    
    public int getLeaveId() {
        return this.leaveId;
    }
    
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public HrPostHistory getHrPostHistory() {
        return this.hrPostHistory;
    }
    
    public void setHrPostHistory(HrPostHistory hrPostHistory) {
        this.hrPostHistory = hrPostHistory;
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

    public Concept getLeaveType() {
        return this.leaveType;
    }
    
    public void setLeaveType(Concept leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveTypeOther() {
        return this.leaveTypeOther;
    }
    
    public void setLeaveTypeOther(String leaveTypeOther) {
        this.leaveTypeOther = leaveTypeOther;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getLeaveId();
	}

	public void setId(Integer id) {
		setLeaveId(id);
	}
}
