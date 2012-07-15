package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.openmrs.BaseOpenmrsData;



public class HrTrainingClass extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int trainingClassId;
     private HrTraining hrTraining;
     private Date startDate;
     private float duration;
     private float ceunits;
     private String location;
     private String instructor;
     private String organization;
     private String fundingSource;
     private float costCourse;
     private float costRegister;
     private float costTravel;
     private float costPerdiem;
     private String uuid;
     private Double sortWeight;
     private Set<HrTrainPerson> hrTrainPersons = new HashSet<HrTrainPerson>(0);


    // Constructors

    /** default constructor */
    public HrTrainingClass() {
    }

	/** minimal constructor */
    public HrTrainingClass(int trainClassId, HrTraining hrTraining, Date startDate, float duration, float ceunits, String location, String instructor, String organization, String fundingSource, float costCourse, float costRegister, float costTravel, float costPerdiem, String uuid) {
        this.trainingClassId = trainClassId;
        this.hrTraining = hrTraining;
        this.startDate = startDate;
        this.duration = duration;
        this.ceunits = ceunits;
        this.location = location;
        this.instructor = instructor;
        this.organization = organization;
        this.fundingSource = fundingSource;
        this.costCourse = costCourse;
        this.costRegister = costRegister;
        this.costTravel = costTravel;
        this.costPerdiem = costPerdiem;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrTrainingClass(int trainClassId, HrTraining hrTraining, Date startDate, float duration, float ceunits, String location, String instructor, String organization, String fundingSource, float costCourse, float costRegister, float costTravel, float costPerdiem, String uuid, Double sortWeight, Set<HrTrainPerson> hrTrainPersons) {
        this.trainingClassId = trainClassId;
        this.hrTraining = hrTraining;
        this.startDate = startDate;
        this.duration = duration;
        this.ceunits = ceunits;
        this.location = location;
        this.instructor = instructor;
        this.organization = organization;
        this.fundingSource = fundingSource;
        this.costCourse = costCourse;
        this.costRegister = costRegister;
        this.costTravel = costTravel;
        this.costPerdiem = costPerdiem;
        this.uuid = uuid;
        this.sortWeight = sortWeight;
        this.hrTrainPersons = hrTrainPersons;
    }


    // Property accessors

    public int getTrainingClassId() {
        return this.trainingClassId;
    }
    
    public void setTrainingClassId(int trainingClassId) {
        this.trainingClassId = trainingClassId;
    }


    public HrTraining getHrTraining() {
		return hrTraining;
	}

	public void setHrTraining(HrTraining hrTraining) {
		this.hrTraining = hrTraining;
	}

	public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public float getDuration() {
        return this.duration;
    }
    
    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getCeunits() {
        return this.ceunits;
    }
    
    public void setCeunits(float ceunits) {
        this.ceunits = ceunits;
    }

    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return this.instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getOrganization() {
        return this.organization;
    }
    
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFundingSource() {
        return this.fundingSource;
    }
    
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public float getCostCourse() {
        return this.costCourse;
    }
    
    public void setCostCourse(float costCourse) {
        this.costCourse = costCourse;
    }

    public float getCostRegister() {
        return this.costRegister;
    }
    
    public void setCostRegister(float costRegister) {
        this.costRegister = costRegister;
    }

    public float getCostTravel() {
        return this.costTravel;
    }
    
    public void setCostTravel(float costTravel) {
        this.costTravel = costTravel;
    }

    public float getCostPerdiem() {
        return this.costPerdiem;
    }
    
    public void setCostPerdiem(float costPerdiem) {
        this.costPerdiem = costPerdiem;
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

    public Set<HrTrainPerson> getHrTrainPersons() {
        return this.hrTrainPersons;
    }
    
    public void setHrTrainPersons(Set<HrTrainPerson> hrTrainPersons) {
        this.hrTrainPersons = hrTrainPersons;
    }

	public Integer getId() {
		return getTrainingClassId();
	}

	public void setId(Integer id) {
		setTrainingClassId(id);
	}



}
