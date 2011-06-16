package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class HrPost  implements java.io.Serializable {


    // Fields    

     private int postId;
     private HrJobTitle hrJobTitle;
     private Integer nationalId;
     private int locationId;
     private String minGrade;
     private String maxGrade;
     private String timeBasis;
     private String status;
     private String fundingSource;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short retired;
     private Integer retiredBy;
     private Date dateRetired;
     private String retireReason;
     private String uuid;
     private Set hrPostHistories = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrPost() {
    }

	/** minimal constructor */
    public HrPost(int postId, HrJobTitle hrJobTitle, int locationId, int creator, Date dateCreated, short retired, String uuid) {
        this.postId = postId;
        this.hrJobTitle = hrJobTitle;
        this.locationId = locationId;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.retired = retired;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrPost(int postId, HrJobTitle hrJobTitle, Integer nationalId, int locationId, String minGrade, String maxGrade, String timeBasis, String status, String fundingSource, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short retired, Integer retiredBy, Date dateRetired, String retireReason, String uuid, Set hrPostHistories) {
        this.postId = postId;
        this.hrJobTitle = hrJobTitle;
        this.nationalId = nationalId;
        this.locationId = locationId;
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
        this.timeBasis = timeBasis;
        this.status = status;
        this.fundingSource = fundingSource;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.retired = retired;
        this.retiredBy = retiredBy;
        this.dateRetired = dateRetired;
        this.retireReason = retireReason;
        this.uuid = uuid;
        this.hrPostHistories = hrPostHistories;
    }
    

   
    // Property accessors

    public int getPostId() {
        return this.postId;
    }
    
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public HrJobTitle getHrJobTitle() {
        return this.hrJobTitle;
    }
    
    public void setHrJobTitle(HrJobTitle hrJobTitle) {
        this.hrJobTitle = hrJobTitle;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }
    
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public int getLocationId() {
        return this.locationId;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getMinGrade() {
        return this.minGrade;
    }
    
    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }

    public String getMaxGrade() {
        return this.maxGrade;
    }
    
    public void setMaxGrade(String maxGrade) {
        this.maxGrade = maxGrade;
    }

    public String getTimeBasis() {
        return this.timeBasis;
    }
    
    public void setTimeBasis(String timeBasis) {
        this.timeBasis = timeBasis;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getFundingSource() {
        return this.fundingSource;
    }
    
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
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

    public Set getHrPostHistories() {
        return this.hrPostHistories;
    }
    
    public void setHrPostHistories(Set hrPostHistories) {
        this.hrPostHistories = hrPostHistories;
    }
   








}
