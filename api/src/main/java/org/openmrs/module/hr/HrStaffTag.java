package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class HrStaffTag  implements java.io.Serializable {


    // Fields    

     private int staffTagId;
     private String name;
     private String desciption;
     private int creator;
     private Date dateCreated;
     private short retired;
     private Integer retiredBy;
     private Date dateRetired;
     private String retireReason;
     private String uuid;
    // private Set hrStaffTagMaps = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrStaffTag() {
    }

	/** minimal constructor */
    public HrStaffTag(int staffTagId, int creator, Date dateCreated, short retired, String uuid) {
        this.staffTagId = staffTagId;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.retired = retired;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffTag(int staffTagId, String name, String desciption, int creator, Date dateCreated, short retired, Integer retiredBy, Date dateRetired, String retireReason, String uuid, Set hrStaffTagMaps) {
        this.staffTagId = staffTagId;
        this.name = name;
        this.desciption = desciption;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.retired = retired;
        this.retiredBy = retiredBy;
        this.dateRetired = dateRetired;
        this.retireReason = retireReason;
        this.uuid = uuid;
     //   this.hrStaffTagMaps = hrStaffTagMaps;
    }
    

   
    // Property accessors

    public int getStaffTagId() {
        return this.staffTagId;
    }
    
    public void setStaffTagId(int staffTagId) {
        this.staffTagId = staffTagId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return this.desciption;
    }
    
    public void setDesciption(String desciption) {
        this.desciption = desciption;
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

   /* public Set getHrStaffTagMaps() {
        return this.hrStaffTagMaps;
    }
    
    public void setHrStaffTagMaps(Set hrStaffTagMaps) {
        this.hrStaffTagMaps = hrStaffTagMaps;
    }
   */








}
