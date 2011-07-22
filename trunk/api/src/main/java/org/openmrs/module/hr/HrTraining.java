package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;


public class HrTraining extends BaseOpenmrsMetadata implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int trainingId;
     private Integer nationalId;
     private String category;
     private String uuid;
     private Double sortWeight;
     private Set<HrTrainingClass> hrTrainingClasses = new HashSet<HrTrainingClass>(0);



    // Constructors

    /** default constructor */
    public HrTraining() {
    }

	/** minimal constructor */
    public HrTraining(int trainingId, String category,  String uuid) {
        this.trainingId = trainingId;
        this.category = category;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrTraining(int trainingId, Integer nationalId, String category,  String uuid, Double sortWeight, Set<HrTrainingClass> hrTrainingClasses) {
        this.trainingId = trainingId;
        this.nationalId = nationalId;
        this.category = category;
        this.uuid = uuid;
        this.sortWeight = sortWeight;
        this.hrTrainingClasses = hrTrainingClasses;
    }
    

   
    // Property accessors

    public int getTrainingId() {
        return this.trainingId;
    }
    
    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public Integer getNationalId() {
        return this.nationalId;
    }
    
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
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

    public Set<HrTrainingClass> getHrTrainingClasses() {
        return this.hrTrainingClasses;
    }
    
    public void setHrTrainingClasses(Set<HrTrainingClass> hrTrainingClasses) {
        this.hrTrainingClasses = hrTrainingClasses;
    }

	public Integer getId() {
		return getTrainingId();
	}

	public void setId(Integer id) {
		setTrainingId(id);
	}

}
