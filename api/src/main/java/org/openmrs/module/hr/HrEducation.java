package org.openmrs.module.hr;

import org.openmrs.BaseOpenmrsData;


public class HrEducation extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int educationId;
     private HrStaff hrStaff;
     private String degree;
     private String institution;
     private String institutionLocation;
     private String major;
     private Integer degreeYear;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrEducation() {
    }

	/** minimal constructor */
    public HrEducation(int educationId, HrStaff hrStaff, String degree, String institution, String institutionLocation, String major, String uuid) {
        this.educationId = educationId;
        this.hrStaff = hrStaff;
        this.degree = degree;
        this.institution = institution;
        this.institutionLocation = institutionLocation;
        this.major = major;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrEducation(int educationId, HrStaff hrStaff, String degree, String institution, String institutionLocation, String major, Integer degreeYear, String uuid) {
        this.educationId = educationId;
        this.hrStaff = hrStaff;
        this.degree = degree;
        this.institution = institution;
        this.institutionLocation = institutionLocation;
        this.major = major;
        this.degreeYear = degreeYear;
        this.uuid = uuid;
    }
    

   
    // Property accessors

    public int getEducationId() {
        return this.educationId;
    }
    
    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public String getDegree() {
        return this.degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return this.institution;
    }
    
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstitutionLocation() {
        return this.institutionLocation;
    }
    
    public void setInstitutionLocation(String institutionLocation) {
        this.institutionLocation = institutionLocation;
    }

    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getDegreeYear() {
        return this.degreeYear;
    }
    
    public void setDegreeYear(Integer degreeYear) {
        this.degreeYear = degreeYear;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getEducationId();
	}

	public void setId(Integer id) {
		setEducationId(id);
	}
   








}
