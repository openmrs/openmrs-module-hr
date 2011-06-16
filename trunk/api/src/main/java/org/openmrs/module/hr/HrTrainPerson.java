package org.openmrs.module.hr;

import java.util.Date;


public class HrTrainPerson  implements java.io.Serializable {


    // Fields    

     private int trainPersonId;
     private HrTrainingClass hrTrainingClass;
     private int personId;
     private short completed;
     private String reason;
     private Date followUpDate;
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
    public HrTrainPerson() {
    }

	/** minimal constructor */
    public HrTrainPerson(int trainPersonId, HrTrainingClass hrTrainingClass, int personId, short completed, String reason, Date followUpDate, int creator, Date dateCreated, short voided, String uuid) {
        this.trainPersonId = trainPersonId;
        this.hrTrainingClass = hrTrainingClass;
        this.personId = personId;
        this.completed = completed;
        this.reason = reason;
        this.followUpDate = followUpDate;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrTrainPerson(int trainPersonId, HrTrainingClass hrTrainingClass, int personId, short completed, String reason, Date followUpDate, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid) {
        this.trainPersonId = trainPersonId;
        this.hrTrainingClass = hrTrainingClass;
        this.personId = personId;
        this.completed = completed;
        this.reason = reason;
        this.followUpDate = followUpDate;
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

    public int getTrainPersonId() {
        return this.trainPersonId;
    }
    
    public void setTrainPersonId(int trainPersonId) {
        this.trainPersonId = trainPersonId;
    }

    public HrTrainingClass getHrTrainingClass() {
        return this.hrTrainingClass;
    }
    
    public void setHrTrainingClass(HrTrainingClass hrTrainingClass) {
        this.hrTrainingClass = hrTrainingClass;
    }

    public int getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public short getCompleted() {
        return this.completed;
    }
    
    public void setCompleted(short completed) {
        this.completed = completed;
    }

    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getFollowUpDate() {
        return this.followUpDate;
    }
    
    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
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
