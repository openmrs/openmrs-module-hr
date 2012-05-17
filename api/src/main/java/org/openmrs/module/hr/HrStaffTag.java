package org.openmrs.module.hr;

import org.openmrs.BaseOpenmrsMetadata;



public class HrStaffTag extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int staffTagId;
     private String uuid;



    // Constructors

    /** default constructor */
    public HrStaffTag() {
    }

	/** full constructor */
    public HrStaffTag(int staffTagId, String uuid){
        this.staffTagId = staffTagId;
        this.uuid = uuid;
    }
    

   
    // Property accessors

    public int getStaffTagId() {
        return this.staffTagId;
    }
    
    public void setStaffTagId(int staffTagId) {
        this.staffTagId = staffTagId;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getStaffTagId();
	}

	public void setId(Integer id) {
		setStaffTagId(id);
	}

 







}
