package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;



public class HrCertificate extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer certificateId;
     private Integer nationalId;
     private String agency;
     private String certificate;
     private String levels;
     private String uuid;
     private Set<HrStaffCert> hrStaffCerts = new HashSet<HrStaffCert>(0);


    // Constructors

    /** default constructor */
    public HrCertificate() {
    }

	/** minimal constructor */
    public HrCertificate(Integer certificateId, String agency, String certificate, String levels, String uuid) {
        this.certificateId = certificateId;
        this.agency = agency;
        this.certificate = certificate;
        this.levels = levels;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrCertificate(Integer certificateId, Integer nationalId, String agency, String certificate, String levels, String uuid, Set<HrStaffCert> hrStaffCerts) {
        this.certificateId = certificateId;
        this.nationalId = nationalId;
        this.agency = agency;
        this.certificate = certificate;
        this.levels = levels;
        this.uuid = uuid;
        this.hrStaffCerts = hrStaffCerts;
    }
    

   
    // Property accessors

    public Integer getCertificateId() {
        return this.certificateId;
    }
    
    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }
    
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getAgency() {
        return this.agency;
    }
    
    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCertificate() {
        return this.certificate;
    }
    
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getLevels() {
        return this.levels;
    }
    
    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<HrStaffCert> getHrStaffCerts() {
        return this.hrStaffCerts;
    }
    
    public void setHrStaffCerts(Set<HrStaffCert> hrStaffCerts) {
        this.hrStaffCerts = hrStaffCerts;
    }

	public Integer getId() {
		return getCertificateId();
	}

	public void setId(Integer id) {
		setCertificateId(id);
	}
}
