package org.openmrs.module.hr;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;



public class HrStaffCert extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     private int staffCertId;
     private HrCertificate hrCertificate;
     private HrStaff hrStaff;
     private String level;
     private Date initialCertDate;
     private Date currentCertDate;
     private Date certExpirationDate;
     private String certCancel;
     private Date cancelDate;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrStaffCert() {
    }

	/** minimal constructor */
    public HrStaffCert(int staffCertId, HrCertificate hrCertificate, HrStaff hrStaff, String level, Date currentCertDate, String uuid) {
        this.staffCertId = staffCertId;
        this.hrCertificate = hrCertificate;
        this.hrStaff = hrStaff;
        this.level = level;
        this.currentCertDate = currentCertDate;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffCert(int staffCertId, HrCertificate hrCertificate, HrStaff hrStaff, String level, Date initialCertDate, Date currentCertDate, Date certExpirationDate, String certCancel, Date cancelDate, String uuid) {
        this.staffCertId = staffCertId;
        this.hrCertificate = hrCertificate;
        this.hrStaff = hrStaff;
        this.level = level;
        this.initialCertDate = initialCertDate;
        this.currentCertDate = currentCertDate;
        this.certExpirationDate = certExpirationDate;
        this.certCancel = certCancel;
        this.cancelDate = cancelDate;
        this.uuid = uuid;
    }
    

   
    // Property accessors

    public int getStaffCertId() {
        return this.staffCertId;
    }
    
    public void setStaffCertId(int staffCertId) {
        this.staffCertId = staffCertId;
    }

    public HrCertificate getHrCertificate() {
        return this.hrCertificate;
    }
    
    public void setHrCertificate(HrCertificate hrCertificate) {
        this.hrCertificate = hrCertificate;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }

    public Date getInitialCertDate() {
        return this.initialCertDate;
    }
    
    public void setInitialCertDate(Date initialCertDate) {
        this.initialCertDate = initialCertDate;
    }

    public Date getCurrentCertDate() {
        return this.currentCertDate;
    }
    
    public void setCurrentCertDate(Date currentCertDate) {
        this.currentCertDate = currentCertDate;
    }

    public Date getCertExpirationDate() {
        return this.certExpirationDate;
    }
    
    public void setCertExpirationDate(Date certExpirationDate) {
        this.certExpirationDate = certExpirationDate;
    }

    public String getCertCancel() {
        return this.certCancel;
    }
    
    public void setCertCancel(String certCancel) {
        this.certCancel = certCancel;
    }

    public Date getCancelDate() {
        return this.cancelDate;
    }
    
    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getStaffCertId();
	}

	public void setId(Integer id) {
		setStaffCertId(id);
	}
}
