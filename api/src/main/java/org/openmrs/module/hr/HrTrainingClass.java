package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class HrTrainingClass  implements java.io.Serializable {


    // Fields    

     private int trainClassId;
     private int trainingId;
     private Date startDate;
     private float duration;
     private float ceunits;
     private String location;
     private String instructor;
     private String organization;
     private String fundingSource;
     private float costCourse;
     private float costRegister;
     private float costTravel;
     private float costPerdiem;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short voided;
     private Integer voidedBy;
     private Date dateVoided;
     private String voidReason;
     private String uuid;
     private Double sortWeight;
     private Set hrTrainPersons = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrTrainingClass() {
    }

	/** minimal constructor */
    public HrTrainingClass(int trainClassId, int trainingId, Date startDate, float duration, float ceunits, String location, String instructor, String organization, String fundingSource, float costCourse, float costRegister, float costTravel, float costPerdiem, int creator, Date dateCreated, short voided, String uuid) {
        this.trainClassId = trainClassId;
        this.trainingId = trainingId;
        this.startDate = startDate;
        this.duration = duration;
        this.ceunits = ceunits;
        this.location = location;
        this.instructor = instructor;
        this.organization = organization;
        this.fundingSource = fundingSource;
        this.costCourse = costCourse;
        this.costRegister = costRegister;
        this.costTravel = costTravel;
        this.costPerdiem = costPerdiem;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrTrainingClass(int trainClassId, int trainingId, Date startDate, float duration, float ceunits, String location, String instructor, String organization, String fundingSource, float costCourse, float costRegister, float costTravel, float costPerdiem, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid, Double sortWeight, Set hrTrainPersons) {
        this.trainClassId = trainClassId;
        this.trainingId = trainingId;
        this.startDate = startDate;
        this.duration = duration;
        this.ceunits = ceunits;
        this.location = location;
        this.instructor = instructor;
        this.organization = organization;
        this.fundingSource = fundingSource;
        this.costCourse = costCourse;
        this.costRegister = costRegister;
        this.costTravel = costTravel;
        this.costPerdiem = costPerdiem;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.uuid = uuid;
        this.sortWeight = sortWeight;
        this.hrTrainPersons = hrTrainPersons;
    }
    

   
    // Property accessors

    public int getTrainClassId() {
        return this.trainClassId;
    }
    
    public void setTrainClassId(int trainClassId) {
        this.trainClassId = trainClassId;
    }

    public int getTrainingId() {
        return this.trainingId;
    }
    
    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public float getDuration() {
        return this.duration;
    }
    
    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getCeunits() {
        return this.ceunits;
    }
    
    public void setCeunits(float ceunits) {
        this.ceunits = ceunits;
    }

    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return this.instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getOrganization() {
        return this.organization;
    }
    
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFundingSource() {
        return this.fundingSource;
    }
    
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public float getCostCourse() {
        return this.costCourse;
    }
    
    public void setCostCourse(float costCourse) {
        this.costCourse = costCourse;
    }

    public float getCostRegister() {
        return this.costRegister;
    }
    
    public void setCostRegister(float costRegister) {
        this.costRegister = costRegister;
    }

    public float getCostTravel() {
        return this.costTravel;
    }
    
    public void setCostTravel(float costTravel) {
        this.costTravel = costTravel;
    }

    public float getCostPerdiem() {
        return this.costPerdiem;
    }
    
    public void setCostPerdiem(float costPerdiem) {
        this.costPerdiem = costPerdiem;
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

    public Double getSortWeight() {
        return this.sortWeight;
    }
    
    public void setSortWeight(Double sortWeight) {
        this.sortWeight = sortWeight;
    }

    public Set getHrTrainPersons() {
        return this.hrTrainPersons;
    }
    
    public void setHrTrainPersons(Set hrTrainPersons) {
        this.hrTrainPersons = hrTrainPersons;
    }
   








}
