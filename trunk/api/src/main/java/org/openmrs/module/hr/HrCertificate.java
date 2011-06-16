package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class HrCertificate  implements java.io.Serializable {


    // Fields    

     private int certificateId;
     private Integer nationalId;
     private String agency;
     private String certificate;
     private String levels;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short retired;
     private Integer retiredBy;
     private Date dateRetired;
     private String retireReason;
     private String uuid;
     private Set hrStaffCerts = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrCertificate() {
    }

	/** minimal constructor */
    public HrCertificate(int certificateId, String agency, String certificate, String levels, int creator, Date dateCreated, short retired, String uuid) {
        this.certificateId = certificateId;
        this.agency = agency;
        this.certificate = certificate;
        this.levels = levels;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.retired = retired;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrCertificate(int certificateId, Integer nationalId, String agency, String certificate, String levels, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short retired, Integer retiredBy, Date dateRetired, String retireReason, String uuid, Set hrStaffCerts) {
        this.certificateId = certificateId;
        this.nationalId = nationalId;
        this.agency = agency;
        this.certificate = certificate;
        this.levels = levels;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.retired = retired;
        this.retiredBy = retiredBy;
        this.dateRetired = dateRetired;
        this.retireReason = retireReason;
        this.uuid = uuid;
        this.hrStaffCerts = hrStaffCerts;
    }
    

   
    // Property accessors

    public int getCertificateId() {
        return this.certificateId;
    }
    
    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }
    
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getAgency() {
        return this.agency;
    }
    
    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCertificate() {
        return this.certificate;
    }
    
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getLevels() {
        return this.levels;
    }
    
    public void setLevels(String levels) {
        this.levels = levels;
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

    public Set getHrStaffCerts() {
        return this.hrStaffCerts;
    }
    
    public void setHrStaffCerts(Set hrStaffCerts) {
        this.hrStaffCerts = hrStaffCerts;
    }
   








}
