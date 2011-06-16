package org.openmrs.module.hr;

import java.util.Date;



public class HrStaffAttribute  implements java.io.Serializable {


    // Fields    

     private int staffAttributeId;
     private HrStaffAttributeType hrStaffAttributeType;
     private HrStaff hrStaff;
     private String value;
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
    public HrStaffAttribute() {
    }

	/** minimal constructor */
    public HrStaffAttribute(int staffAttributeId, int creator, Date dateCreated, short voided, String uuid) {
        this.staffAttributeId = staffAttributeId;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffAttribute(int staffAttributeId, HrStaffAttributeType hrStaffAttributeType, HrStaff hrStaff, String value, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid) {
        this.staffAttributeId = staffAttributeId;
        this.hrStaffAttributeType = hrStaffAttributeType;
        this.hrStaff = hrStaff;
        this.value = value;
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

    public int getStaffAttributeId() {
        return this.staffAttributeId;
    }
    
    public void setStaffAttributeId(int staffAttributeId) {
        this.staffAttributeId = staffAttributeId;
    }

    public HrStaffAttributeType getHrStaffAttributeType() {
        return this.hrStaffAttributeType;
    }
    
    public void setHrStaffAttributeType(HrStaffAttributeType hrStaffAttributeType) {
        this.hrStaffAttributeType = hrStaffAttributeType;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
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
