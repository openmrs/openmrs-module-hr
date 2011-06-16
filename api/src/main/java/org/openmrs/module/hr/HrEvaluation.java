package org.openmrs.module.hr;

import java.util.Date;


public class HrEvaluation  implements java.io.Serializable {


    // Fields    

     private int evaluationId;
     private HrStaff hrStaff;
     private HrCompetency hrCompetency;
     private int evaluatorId;
     private String level;
     private Date evaluationDate;
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
    public HrEvaluation() {
    }

	/** minimal constructor */
    public HrEvaluation(int evaluationId, HrStaff hrStaff, HrCompetency hrCompetency, int evaluatorId, String level, Date evaluationDate, int creator, Date dateCreated, short voided, String uuid) {
        this.evaluationId = evaluationId;
        this.hrStaff = hrStaff;
        this.hrCompetency = hrCompetency;
        this.evaluatorId = evaluatorId;
        this.level = level;
        this.evaluationDate = evaluationDate;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrEvaluation(int evaluationId, HrStaff hrStaff, HrCompetency hrCompetency, int evaluatorId, String level, Date evaluationDate, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid) {
        this.evaluationId = evaluationId;
        this.hrStaff = hrStaff;
        this.hrCompetency = hrCompetency;
        this.evaluatorId = evaluatorId;
        this.level = level;
        this.evaluationDate = evaluationDate;
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

    public int getEvaluationId() {
        return this.evaluationId;
    }
    
    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public HrCompetency getHrCompetency() {
        return this.hrCompetency;
    }
    
    public void setHrCompetency(HrCompetency hrCompetency) {
        this.hrCompetency = hrCompetency;
    }

    public int getEvaluatorId() {
        return this.evaluatorId;
    }
    
    public void setEvaluatorId(int evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }

    public Date getEvaluationDate() {
        return this.evaluationDate;
    }
    
    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
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
