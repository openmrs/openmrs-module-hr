package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;


public class HrCompetency extends BaseOpenmrsMetadata  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int competencyId;
     private Integer nationalId;
     private String category;
     private String levels;
     private String editPrivilege;
     private String uuid;
     private Double sortWeight;
     private Set<HrEvaluation> hrEvaluations = new HashSet<HrEvaluation>(0);


    // Constructors

    /** default constructor */
    public HrCompetency() {
    
    }

	/** minimal constructor */
    public HrCompetency(int competencyId, String name, String levels, String uuid) {
        this.competencyId = competencyId;
        this.levels = levels;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrCompetency(int competencyId, Integer nationalId, String category, String name, String levels, String editPrivilege, String uuid, Double sortWeight, Set<HrEvaluation> hrEvaluations) {
        this.competencyId = competencyId;
        this.nationalId = nationalId;
        this.category = category;
        this.levels = levels;
        this.editPrivilege = editPrivilege;
        this.uuid = uuid;
        this.sortWeight = sortWeight;
        this.hrEvaluations = hrEvaluations;
    }
    

   
    // Property accessors

    public int getCompetencyId() {
        return this.competencyId;
    }
    
    public void setCompetencyId(int competencyId) {
        this.competencyId = competencyId;
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

    public String getLevels() {
        return this.levels;
    }
    
    public void setLevels(String levels) {
        this.levels = levels;
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
    public Set<HrEvaluation> getHrEvaluations() {
        return this.hrEvaluations;
    }
    
    public void setHrEvaluations(Set<HrEvaluation> hrEvaluations) {
        this.hrEvaluations = hrEvaluations;
    }

	public Integer getId() {
		return getCompetencyId();
	}

	public void setId(Integer id) {
		setCompetencyId(id);
	}
   








}
