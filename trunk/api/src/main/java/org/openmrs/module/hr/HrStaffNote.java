package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;


public class HrStaffNote extends BaseOpenmrsData implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private int staffNoteId;
     private HrStaff hrStaff;
     private HrStaffNote hrStaffNote;
     private String staffNoteType;
     private String text;
     private Integer priority;
     private String uuid;
     private Set<HrStaffNote> hrStaffNotes = new HashSet<HrStaffNote>(0);


    // Constructors

    /** default constructor */
    public HrStaffNote() {
    }

	/** minimal constructor */
    public HrStaffNote(int staffNoteId, String text, String uuid) {
        this.staffNoteId = staffNoteId;
        this.text = text;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffNote(int staffNoteId, HrStaff hrStaff, HrStaffNote hrStaffNote, String staffNoteType, String text, Integer priority, String uuid, Set<HrStaffNote> hrStaffNotes) {
        this.staffNoteId = staffNoteId;
        this.hrStaff = hrStaff;
        this.hrStaffNote = hrStaffNote;
        this.staffNoteType = staffNoteType;
        this.text = text;
        this.priority = priority;
        this.uuid = uuid;
        this.hrStaffNotes = hrStaffNotes;
    }
    

   
    // Property accessors

    public int getStaffNoteId() {
        return this.staffNoteId;
    }
    
    public void setStaffNoteId(int staffNoteId) {
        this.staffNoteId = staffNoteId;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public HrStaffNote getHrStaffNote() {
        return this.hrStaffNote;
    }
    
    public void setHrStaffNote(HrStaffNote hrStaffNote) {
        this.hrStaffNote = hrStaffNote;
    }

    public String getStaffNoteType() {
        return this.staffNoteType;
    }
    
    public void setStaffNoteType(String staffNoteType) {
        this.staffNoteType = staffNoteType;
    }

    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }

    public Integer getPriority() {
        return this.priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<HrStaffNote> getHrStaffNotes() {
        return this.hrStaffNotes;
    }
    
    public void setHrStaffNotes(Set<HrStaffNote> hrStaffNotes) {
        this.hrStaffNotes = hrStaffNotes;
    }

	public Integer getId() {
		return getStaffNoteId();
	}

	public void setId(Integer id) {
		setStaffNoteId(id);
	}
   








}
