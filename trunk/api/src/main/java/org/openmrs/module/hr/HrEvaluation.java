package org.openmrs.module.hr;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Person;


public class HrEvaluation extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     private int evaluationId;
     private HrStaff hrStaff;
     private HrCompetency hrCompetency;
     private Person evaluator;
     private String level;
     private Date evaluationDate;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrEvaluation() {
    }
 
    /** full constructor */
    public HrEvaluation(int evaluationId, HrStaff hrStaff, HrCompetency hrCompetency, Person evaluator, String level, Date evaluationDate, String uuid) {
        this.evaluationId = evaluationId;
        this.hrStaff = hrStaff;
        this.hrCompetency = hrCompetency;
        this.evaluator = evaluator;
        this.level = level;
        this.evaluationDate = evaluationDate;
        this.uuid = uuid;
    }
    

   
    // Property accessors

    public int getEvaluationId() {
        return this.evaluationId;
    }
    
    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public HrCompetency getHrCompetency() {
        return this.hrCompetency;
    }
    
    public void setHrCompetency(HrCompetency hrCompetency) {
        this.hrCompetency = hrCompetency;
    }

    public Person getEvaluator() {
        return this.evaluator;
    }
    
    public void setEvaluator(Person evaluator) {
        this.evaluator = evaluator;
    }

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }

    public Date getEvaluationDate() {
        return this.evaluationDate;
    }
    
    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getEvaluationId();
	}

	public void setId(Integer id) {
		setEvaluationId(id);
	}
 
}
