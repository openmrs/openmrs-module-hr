package org.openmrs.module.hr;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Person;


public class HrTrainPerson extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int trainPersonId;
     private HrTrainingClass hrTrainingClass;
     private Person person;
     private short completed;
     private String reason;
     private Date followUpDate;
     private String uuid;


    // Constructors

    /** default constructor */
    public HrTrainPerson() {
    }

    /** full constructor */
    public HrTrainPerson(int trainPersonId, HrTrainingClass hrTrainingClass, Person person, short completed, String reason, Date followUpDate,  String uuid) {
        this.trainPersonId = trainPersonId;
        this.hrTrainingClass = hrTrainingClass;
        this.person = person;
        this.completed = completed;
        this.reason = reason;
        this.followUpDate = followUpDate;
        this.uuid = uuid;
    }
    

   
    // Property accessors

    public int getTrainPersonId() {
        return this.trainPersonId;
    }
    
    public void setTrainPersonId(int trainPersonId) {
        this.trainPersonId = trainPersonId;
    }

    public HrTrainingClass getHrTrainingClass() {
        return this.hrTrainingClass;
    }
    
    public void setHrTrainingClass(HrTrainingClass hrTrainingClass) {
        this.hrTrainingClass = hrTrainingClass;
    }

    public Person getPerson() {
        return this.person;
    }
    
    public void setPerson(Person person) {
        this.person= person;
    }

    public short getCompleted() {
        return this.completed;
    }
    
    public void setCompleted(short completed) {
        this.completed = completed;
    }

    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getFollowUpDate() {
        return this.followUpDate;
    }
    
    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getTrainPersonId();
	}

	public void setId(Integer id) {
		setTrainPersonId(id);
	}
}
