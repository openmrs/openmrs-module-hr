package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HrStaffAttributeType  implements java.io.Serializable {


    // Fields    

     private int staffAttributeTypeId;
     private String name;
     private String description;
     private String handlerType;
     private String handlerConfiguration;
     private int minOccurs;
     private Integer maxOccurs;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short retired;
     private Integer retiredBy;
     private Date dateRetired;
     private String retireReason;
     private String editPrivilege;
     private String uuid;
     private Double sortWeight;
     private Set hrStaffAttributes = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrStaffAttributeType() {
    }

	/** minimal constructor */
    public HrStaffAttributeType(int staffAttributeTypeId, String name, String description, String handlerType, int minOccurs, int creator, Date dateCreated, short retired, String uuid) {
        this.staffAttributeTypeId = staffAttributeTypeId;
        this.name = name;
        this.description = description;
        this.handlerType = handlerType;
        this.minOccurs = minOccurs;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.retired = retired;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffAttributeType(int staffAttributeTypeId, String name, String description, String handlerType, String handlerConfiguration, int minOccurs, Integer maxOccurs, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short retired, Integer retiredBy, Date dateRetired, String retireReason, String editPrivilege, String uuid, Double sortWeight, Set hrStaffAttributes) {
        this.staffAttributeTypeId = staffAttributeTypeId;
        this.name = name;
        this.description = description;
        this.handlerType = handlerType;
        this.handlerConfiguration = handlerConfiguration;
        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.retired = retired;
        this.retiredBy = retiredBy;
        this.dateRetired = dateRetired;
        this.retireReason = retireReason;
        this.editPrivilege = editPrivilege;
        this.uuid = uuid;
        this.sortWeight = sortWeight;
        this.hrStaffAttributes = hrStaffAttributes;
    }
    

   
    // Property accessors

    public int getStaffAttributeTypeId() {
        return this.staffAttributeTypeId;
    }
    
    public void setStaffAttributeTypeId(int staffAttributeTypeId) {
        this.staffAttributeTypeId = staffAttributeTypeId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getHandlerType() {
        return this.handlerType;
    }
    
    public void setHandlerType(String handlerType) {
        this.handlerType = handlerType;
    }

    public String getHandlerConfiguration() {
        return this.handlerConfiguration;
    }
    
    public void setHandlerConfiguration(String handlerConfiguration) {
        this.handlerConfiguration = handlerConfiguration;
    }

    public int getMinOccurs() {
        return this.minOccurs;
    }
    
    public void setMinOccurs(int minOccurs) {
        this.minOccurs = minOccurs;
    }

    public Integer getMaxOccurs() {
        return this.maxOccurs;
    }
    
    public void setMaxOccurs(Integer maxOccurs) {
        this.maxOccurs = maxOccurs;
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

    public String getEditPrivilege() {
        return this.editPrivilege;
    }
    
    public void setEditPrivilege(String editPrivilege) {
        this.editPrivilege = editPrivilege;
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

    public Set getHrStaffAttributes() {
        return this.hrStaffAttributes;
    }
    
    public void setHrStaffAttributes(Set hrStaffAttributes) {
        this.hrStaffAttributes = hrStaffAttributes;
    }
   








}
