package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.Concept;
import org.openmrs.Location;


public class HrPost extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    

     private int postId;
     private HrJobTitle hrJobTitle;
     private Integer nationalId;
     private Location location;
     private String timeBasis;
     private Concept status;
     private String fundingSource;
     private String uuid;
     private Set hrPostHistories = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrPost() {
    }

	/** minimal constructor */
    public HrPost(int postId, HrJobTitle hrJobTitle, Location location,String uuid) {
        this.postId = postId;
        this.hrJobTitle = hrJobTitle;
        this.location = location;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrPost(int postId, HrJobTitle hrJobTitle, Integer nationalId, Location location, String timeBasis, Concept status, String fundingSource, String uuid, Set hrPostHistories) {
        this.postId = postId;
        this.hrJobTitle = hrJobTitle;
        this.nationalId = nationalId;
        this.location = location;
        this.timeBasis = timeBasis;
        this.status = status;
        this.fundingSource = fundingSource;
        this.uuid = uuid;
        this.hrPostHistories = hrPostHistories;
    }
    

   
    // Property accessors

    public int getPostId() {
        return this.postId;
    }
    
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public HrJobTitle getHrJobTitle() {
        return this.hrJobTitle;
    }
    
    public void setHrJobTitle(HrJobTitle hrJobTitle) {
        this.hrJobTitle = hrJobTitle;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }
    
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTimeBasis() {
        return this.timeBasis;
    }
    
    public void setTimeBasis(String timeBasis) {
        this.timeBasis = timeBasis;
    }

    public Concept getStatus() {
        return this.status;
    }
    
    public void setStatus(Concept status) {
        this.status = status;
    }

    public String getFundingSource() {
        return this.fundingSource;
    }
    
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set getHrPostHistories() {
        return this.hrPostHistories;
    }
    
    public void setHrPostHistories(Set hrPostHistories) {
        this.hrPostHistories = hrPostHistories;
    }

	public Integer getId() {
		return getPostId();
	}

	public void setId(Integer id) {
		setPostId(id);
	}

}
