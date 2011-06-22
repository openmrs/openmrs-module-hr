package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class HrJobTitle  implements java.io.Serializable {


    // Fields    

     private int jobId;
     private HrIscoCodes hrIscoCodes;
     private Integer nationalId;
     private String title;
     private int cadre;
     private String grades;
     private String description;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short retired;
     private Integer retiredBy;
     private Date dateRetired;
     private String retireReason;
     private String uuid;
     private Set hrPosts = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrJobTitle() {
    }

	/** minimal constructor */
    public HrJobTitle(int jobId, String title, int cadre, String grades, int creator, Date dateCreated, short retired, String uuid) {
        this.jobId = jobId;
        this.title = title;
        this.cadre = cadre;
        this.grades = grades;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.retired = retired;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrJobTitle(int jobId, HrIscoCodes hrIscoCodes, Integer nationalId, String title, int cadre, String grades, String description, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short retired, Integer retiredBy, Date dateRetired, String retireReason, String uuid, Set hrPosts) {
        this.jobId = jobId;
        this.hrIscoCodes = hrIscoCodes;
        this.nationalId = nationalId;
        this.title = title;
        this.cadre = cadre;
        this.grades = grades;
        this.description = description;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.retired = retired;
        this.retiredBy = retiredBy;
        this.dateRetired = dateRetired;
        this.retireReason = retireReason;
        this.uuid = uuid;
        this.hrPosts = hrPosts;
    }
    

   
    // Property accessors

    public int getJobId() {
        return this.jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public HrIscoCodes getHrIscoCodes() {
        return this.hrIscoCodes;
    }
    
    public void setHrIscoCodes(HrIscoCodes hrIscoCodes) {
        this.hrIscoCodes = hrIscoCodes;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }
    
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public int getCadre() {
        return this.cadre;
    }
    
    public void setCadre(int cadre) {
        this.cadre = cadre;
    }

    public String getGrades() {
        return this.grades;
    }
    
    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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

    public Set getHrPosts() {
        return this.hrPosts;
    }
    
    public void setHrPosts(Set hrPosts) {
        this.hrPosts = hrPosts;
    }
   








}
