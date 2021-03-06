package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.Concept;


public class HrJobTitle extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    
	

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int jobId;
     private HrIscoCodes hrIscoCodes;
     private Integer nationalId;
     private String title;
     private Concept cadre;
     private String grades;
     private String uuid;
     private Set<HrPost> hrPosts = new HashSet<HrPost>(0);


    // Constructors

    /** default constructor */
    public HrJobTitle() {
    }

	/** minimal constructor */
    public HrJobTitle(int jobId, String title, Concept cadre, String grades, String uuid) {
        this.jobId = jobId;
        this.title = title;
        this.cadre = cadre;
        this.grades = grades;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrJobTitle(int jobId, HrIscoCodes hrIscoCodes, Integer nationalId, String title, Concept cadre, String grades, String uuid, Set<HrPost> hrPosts) {
        this.jobId = jobId;
        this.hrIscoCodes = hrIscoCodes;
        this.nationalId = nationalId;
        this.title = title;
        this.cadre = cadre;
        this.grades = grades;
        this.uuid = uuid;
        this.hrPosts = hrPosts;
    }
    

   
    // Property accessors

    public int getJobId() {
        return this.jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public HrIscoCodes getHrIscoCodes() {
        return this.hrIscoCodes;
    }
    
    public void setHrIscoCodes(HrIscoCodes hrIscoCodes) {
        this.hrIscoCodes = hrIscoCodes;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }
    
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Concept getCadre() {
        return this.cadre;
    }
    
    public void setCadre(Concept cadre) {
        this.cadre = cadre;
    }

    public String getGrades() {
        return this.grades;
    }
    
    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<HrPost> getHrPosts() {
        return this.hrPosts;
    }
    
    public void setHrPosts(Set<HrPost> hrPosts) {
        this.hrPosts = hrPosts;
    }

	public Integer getId() {
		return getJobId();
	}

	public void setId(Integer id) {
		setJobId(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HrJobTitle other = (HrJobTitle) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.jobId+"";
	}

}
