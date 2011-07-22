package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;

public class HrStaffAttributeType extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int staffAttributeTypeId;
     private String format;
     private Integer foreignKey;
     private Boolean searchable=false;
     private String editPrivilege;
     private String uuid;
     private Double sortWeight;
     private Set<HrStaffAttribute> hrStaffAttributes = new HashSet<HrStaffAttribute>(0);


    // Constructors

    /** default constructor */
    public HrStaffAttributeType() {
    }

 
    public HrStaffAttributeType(int staffAttributeTypeId, String format,Integer foreignKey, Boolean searchable, String editPrivilege,String uuid, Double sortWeight, Set<HrStaffAttribute> hrStaffAttributes) {
		this.staffAttributeTypeId = staffAttributeTypeId;
		this.format = format;
		this.foreignKey = foreignKey;
		this.searchable = searchable;
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

    public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public Integer getForeignKey() {
		return foreignKey;
	}


	public void setForeignKey(Integer foreignKey) {
		this.foreignKey = foreignKey;
	}


	public Boolean getSearchable() {
		return searchable;
	}


	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
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

    public Set<HrStaffAttribute> getHrStaffAttributes() {
        return this.hrStaffAttributes;
    }
    
    public void setHrStaffAttributes(Set<HrStaffAttribute> hrStaffAttributes) {
        this.hrStaffAttributes = hrStaffAttributes;
    }

	public Integer getId() {
		return getStaffAttributeTypeId();
	}

	public void setId(Integer id) {
		setStaffAttributeTypeId(id);
	}
}
