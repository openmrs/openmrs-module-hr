package org.openmrs.module.hr;

import java.util.Date;

public class HrAssignment  implements java.io.Serializable {


    // Fields    

     private int assignmentId;
     private HrPostHistory hrPostHistory;
     private HrStaff hrStaff;
     private int locationId;
     private String assignment;
     private String grade;
     private String timeBasis;
     private String workSchedule;
     private Date startDate;
     private Date endDate;
     private String endReason;
     private String endReasonOther;
     private String note;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short voided;
     private Integer voidedBy;
     private Date dateVoided;
     private String voidReason;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrAssignment() {
    }

	/** minimal constructor */
    public HrAssignment(int assignmentId, HrPostHistory hrPostHistory, int locationId, int creator, Date dateCreated, short voided, String uuid) {
        this.assignmentId = assignmentId;
        this.hrPostHistory = hrPostHistory;
        this.locationId = locationId;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrAssignment(int assignmentId, HrPostHistory hrPostHistory, HrStaff hrStaff, int locationId, String assignment, String grade, String timeBasis, String workSchedule, Date startDate, Date endDate, String endReason, String endReasonOther, String note, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid) {
        this.assignmentId = assignmentId;
        this.hrPostHistory = hrPostHistory;
        this.hrStaff = hrStaff;
        this.locationId = locationId;
        this.assignment = assignment;
        this.grade = grade;
        this.timeBasis = timeBasis;
        this.workSchedule = workSchedule;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endReason = endReason;
        this.endReasonOther = endReasonOther;
        this.note = note;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
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

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public int getLocationId() {
        return this.locationId;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAssignment() {
        return this.assignment;
    }
    
    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTimeBasis() {
        return this.timeBasis;
    }
    
    public void setTimeBasis(String timeBasis) {
        this.timeBasis = timeBasis;
    }

    public String getWorkSchedule() {
        return this.workSchedule;
    }
    
    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
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

    public String getEndReason() {
        return this.endReason;
    }
    
    public void setEndReason(String endReason) {
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

    public short getVoided() {
        return this.voided;
    }
    
    public void setVoided(short voided) {
        this.voided = voided;
    }

    public Integer getVoidedBy() {
        return this.voidedBy;
    }
    
    public void setVoidedBy(Integer voidedBy) {
        this.voidedBy = voidedBy;
    }

    public Date getDateVoided() {
        return this.dateVoided;
    }
    
    public void setDateVoided(Date dateVoided) {
        this.dateVoided = dateVoided;
    }

    public String getVoidReason() {
        return this.voidReason;
    }
    
    public void setVoidReason(String voidReason) {
        this.voidReason = voidReason;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
   








}
