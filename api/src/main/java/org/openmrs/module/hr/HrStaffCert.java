package org.openmrs.module.hr;

import java.util.Date;



public class HrStaffCert  implements java.io.Serializable {


    // Fields    

     private int staffCertId;
     private HrCertificate hrCertificate;
     private HrStaff hrStaff;
     private String level;
     private Date initialCertDate;
     private Date currentCertDate;
     private Date certExpirationDate;
     private String certCancel;
     private Date cancelDate;
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
    public HrStaffCert() {
    }

	/** minimal constructor */
    public HrStaffCert(int staffCertId, HrCertificate hrCertificate, HrStaff hrStaff, String level, Date currentCertDate, int creator, Date dateCreated, short voided, String uuid) {
        this.staffCertId = staffCertId;
        this.hrCertificate = hrCertificate;
        this.hrStaff = hrStaff;
        this.level = level;
        this.currentCertDate = currentCertDate;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffCert(int staffCertId, HrCertificate hrCertificate, HrStaff hrStaff, String level, Date initialCertDate, Date currentCertDate, Date certExpirationDate, String certCancel, Date cancelDate, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid) {
        this.staffCertId = staffCertId;
        this.hrCertificate = hrCertificate;
        this.hrStaff = hrStaff;
        this.level = level;
        this.initialCertDate = initialCertDate;
        this.currentCertDate = currentCertDate;
        this.certExpirationDate = certExpirationDate;
        this.certCancel = certCancel;
        this.cancelDate = cancelDate;
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

    public int getStaffCertId() {
        return this.staffCertId;
    }
    
    public void setStaffCertId(int staffCertId) {
        this.staffCertId = staffCertId;
    }

    public HrCertificate getHrCertificate() {
        return this.hrCertificate;
    }
    
    public void setHrCertificate(HrCertificate hrCertificate) {
        this.hrCertificate = hrCertificate;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }

    public Date getInitialCertDate() {
        return this.initialCertDate;
    }
    
    public void setInitialCertDate(Date initialCertDate) {
        this.initialCertDate = initialCertDate;
    }

    public Date getCurrentCertDate() {
        return this.currentCertDate;
    }
    
    public void setCurrentCertDate(Date currentCertDate) {
        this.currentCertDate = currentCertDate;
    }

    public Date getCertExpirationDate() {
        return this.certExpirationDate;
    }
    
    public void setCertExpirationDate(Date certExpirationDate) {
        this.certExpirationDate = certExpirationDate;
    }

    public String getCertCancel() {
        return this.certCancel;
    }
    
    public void setCertCancel(String certCancel) {
        this.certCancel = certCancel;
    }

    public Date getCancelDate() {
        return this.cancelDate;
    }
    
    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
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
