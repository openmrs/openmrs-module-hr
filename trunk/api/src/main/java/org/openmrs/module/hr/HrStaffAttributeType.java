package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;

public class HrStaffAttributeType extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    

     private int staffAttributeTypeId;
     private String handlerType;
     private String handlerConfiguration;
     private int minOccurs;
     private Integer maxOccurs;
     private String editPrivilege;
     private String uuid;
     private Double sortWeight;
     private Set hrStaffAttributes = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrStaffAttributeType() {
    }

	/** minimal constructor */
    public HrStaffAttributeType(int staffAttributeTypeId,String handlerType, int minOccurs, String uuid) {
        this.staffAttributeTypeId = staffAttributeTypeId;
        this.handlerType = handlerType;
        this.minOccurs = minOccurs;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffAttributeType(int staffAttributeTypeId,String handlerType, String handlerConfiguration, int minOccurs, Integer maxOccurs,String editPrivilege, String uuid, Double sortWeight, Set hrStaffAttributes) {
        this.staffAttributeTypeId = staffAttributeTypeId;
        this.handlerType = handlerType;
        this.handlerConfiguration = handlerConfiguration;
        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
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

	public Integer getId() {
		return getStaffAttributeTypeId();
	}

	public void setId(Integer id) {
		setStaffAttributeTypeId(id);
	}
}
