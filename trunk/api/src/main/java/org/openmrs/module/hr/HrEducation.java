package org.openmrs.module.hr;

import java.util.Date;


public class HrEducation  implements java.io.Serializable {


    // Fields    

     private int educationId;
     private HrStaff hrStaff;
     private String degree;
     private String institution;
     private String institutionLocation;
     private String major;
     private Integer degreeYear;
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
    public HrEducation() {
    }

	/** minimal constructor */
    public HrEducation(int educationId, HrStaff hrStaff, String degree, String institution, String institutionLocation, String major, int creator, Date dateCreated, short voided, String uuid) {
        this.educationId = educationId;
        this.hrStaff = hrStaff;
        this.degree = degree;
        this.institution = institution;
        this.institutionLocation = institutionLocation;
        this.major = major;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrEducation(int educationId, HrStaff hrStaff, String degree, String institution, String institutionLocation, String major, Integer degreeYear, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid) {
        this.educationId = educationId;
        this.hrStaff = hrStaff;
        this.degree = degree;
        this.institution = institution;
        this.institutionLocation = institutionLocation;
        this.major = major;
        this.degreeYear = degreeYear;
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

    public int getEducationId() {
        return this.educationId;
    }
    
    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public String getDegree() {
        return this.degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return this.institution;
    }
    
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstitutionLocation() {
        return this.institutionLocation;
    }
    
    public void setInstitutionLocation(String institutionLocation) {
        this.institutionLocation = institutionLocation;
    }

    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getDegreeYear() {
        return this.degreeYear;
    }
    
    public void setDegreeYear(Integer degreeYear) {
        this.degreeYear = degreeYear;
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
