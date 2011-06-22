package org.openmrs.module.hr;

import java.util.Date;

public class HrLeave  implements java.io.Serializable {


    // Fields    

     private int leaveId;
     private HrPostHistory hrPostHistory;
     private Date startDate;
     private Date endDate;
     private Integer leaveType;
     private String leaveTypeOther;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short retired;
     private Integer retiredBy;
     private Date dateRetired;
     private String retireReason;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrLeave() {
    }

	/** minimal constructor */
    public HrLeave(int leaveId, HrPostHistory hrPostHistory, Date startDate, int creator, Date dateCreated, short retired, String uuid) {
        this.leaveId = leaveId;
        this.hrPostHistory = hrPostHistory;
        this.startDate = startDate;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.retired = retired;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrLeave(int leaveId, HrPostHistory hrPostHistory, Date startDate, Date endDate, Integer leaveType, String leaveTypeOther, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short retired, Integer retiredBy, Date dateRetired, String retireReason, String uuid) {
        this.leaveId = leaveId;
        this.hrPostHistory = hrPostHistory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.leaveTypeOther = leaveTypeOther;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.retired = retired;
        this.retiredBy = retiredBy;
        this.dateRetired = dateRetired;
        this.retireReason = retireReason;
        this.uuid = uuid;
    }
    

   
    // Property accessors

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

    public Integer getLeaveType() {
        return this.leaveType;
    }
    
    public void setLeaveType(Integer leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveTypeOther() {
        return this.leaveTypeOther;
    }
    
    public void setLeaveTypeOther(String leaveTypeOther) {
        this.leaveTypeOther = leaveTypeOther;
    }

    public int getCreator() {
        return this.creator;
    }
    
    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getChangedBy() {
        return this.changedBy;
    }
    
    public void setChangedBy(Integer changedBy) {
        this.changedBy = changedBy;
    }

    public Date getDateChanged() {
        return this.dateChanged;
    }
    
    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public short getRetired() {
        return this.retired;
    }
    
    public void setRetired(short retired) {
        this.retired = retired;
    }

    public Integer getRetiredBy() {
        return this.retiredBy;
    }
    
    public void setRetiredBy(Integer retiredBy) {
        this.retiredBy = retiredBy;
    }

    public Date getDateRetired() {
        return this.dateRetired;
    }
    
    public void setDateRetired(Date dateRetired) {
        this.dateRetired = dateRetired;
    }

    public String getRetireReason() {
        return this.retireReason;
    }
    
    public void setRetireReason(String retireReason) {
        this.retireReason = retireReason;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
   








}
