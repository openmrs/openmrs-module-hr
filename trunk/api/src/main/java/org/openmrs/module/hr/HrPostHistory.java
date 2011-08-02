
package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;



public class HrPostHistory extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int postHistoryId;
     private HrStaff hrStaff;
     private HrPost hrPost;
     private Date startDate;
     private String grade;
     private Date endDate;
     private Concept endReason;
     private String endReasonOther;
     private String uuid;
     private Set<HrAssignment> hrAssignments = new HashSet<HrAssignment>(0);
     private Set<HrLeave> hrLeaves = new HashSet<HrLeave>(0);


    // Constructors

    /** default constructor */
    public HrPostHistory() {
    }

	/** minimal constructor */
    public HrPostHistory(int postHistoryId, HrStaff hrStaff, HrPost hrPost, Date startDate, String uuid) {
        this.postHistoryId = postHistoryId;
        this.hrStaff = hrStaff;
        this.hrPost = hrPost;
        this.startDate = startDate;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrPostHistory(int postHistoryId, HrStaff hrStaff, HrPost hrPost, Date startDate, String grade, Date endDate, Concept endReason, String endReasonOther,  String uuid, Set<HrAssignment> hrAssignments, Set<HrLeave> hrLeaves) {
        this.postHistoryId = postHistoryId;
        this.hrStaff = hrStaff;
        this.hrPost = hrPost;
        this.startDate = startDate;
        this.grade = grade;
        this.endDate = endDate;
        this.endReason = endReason;
        this.endReasonOther = endReasonOther;
        this.uuid = uuid;
        this.hrAssignments = hrAssignments;
        this.hrLeaves = hrLeaves;
    }
    

   
    // Property accessors

    public int getPostHistoryId() {
        return this.postHistoryId;
    }
    
    public void setPostHistoryId(int postHistoryId) {
        this.postHistoryId = postHistoryId;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public HrPost getHrPost() {
        return this.hrPost;
    }
    
    public void setHrPost(HrPost hrPost) {
        this.hrPost = hrPost;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Concept getEndReason() {
        return this.endReason;
    }
    
    public void setEndReason(Concept endReason) {
        this.endReason = endReason;
    }

    public String getEndReasonOther() {
        return this.endReasonOther;
    }
    
    public void setEndReasonOther(String endReasonOther) {
        this.endReasonOther = endReasonOther;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<HrAssignment> getHrAssignments() {
        return this.hrAssignments;
    }
    
    public void setHrAssignments(Set<HrAssignment> hrAssignments) {
        this.hrAssignments = hrAssignments;
    }

    public Set<HrLeave> getHrLeaves() {
        return this.hrLeaves;
    }
    
    public void setHrLeaves(Set<HrLeave> hrLeaves) {
        this.hrLeaves = hrLeaves;
    }

	public Integer getId() {
		return getPostHistoryId();
	}

	public void setId(Integer id) {
		setPostHistoryId(id);
	}
}
