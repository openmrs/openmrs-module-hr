package org.openmrs.module.hr;

import org.openmrs.BaseOpenmrsData;



public class HrStaffAttribute extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int staffAttributeId;
     private HrStaffAttributeType hrStaffAttributeType;
     private HrStaff hrStaff;
     private String value;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrStaffAttribute() {
    }

	/** minimal constructor */
    public HrStaffAttribute(int staffAttributeId, String uuid) {
        this.staffAttributeId = staffAttributeId;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffAttribute(int staffAttributeId, HrStaffAttributeType hrStaffAttributeType, HrStaff hrStaff, String value, String uuid) {
        this.staffAttributeId = staffAttributeId;
        this.hrStaffAttributeType = hrStaffAttributeType;
        this.hrStaff = hrStaff;
        this.value = value;
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
    
    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getStaffAttributeId();
	}

	public void setId(Integer id) {
		setStaffAttributeId(id);
	}
}
