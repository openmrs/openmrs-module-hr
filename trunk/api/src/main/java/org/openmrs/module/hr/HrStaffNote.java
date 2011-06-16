package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class HrStaffNote  implements java.io.Serializable {


    // Fields    

     private int staffNoteId;
     private HrStaffNote hrStaffNote;
     private String staffNoteType;
     private Integer staffId;
     private String text;
     private Integer priority;
     private int creator;
     private Date dateCreated;
     private Integer changedBy;
     private Date dateChanged;
     private short voided;
     private Integer voidedBy;
     private Date dateVoided;
     private String voidReason;
     private String uuid;
     private Set hrStaffNotes = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrStaffNote() {
    }

	/** minimal constructor */
    public HrStaffNote(int staffNoteId, String text, int creator, Date dateCreated, short voided, String uuid) {
        this.staffNoteId = staffNoteId;
        this.text = text;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.uuid = uuid;
    }
    
    /** full constructor */
    public HrStaffNote(int staffNoteId, HrStaffNote hrStaffNote, String staffNoteType, Integer staffId, String text, Integer priority, int creator, Date dateCreated, Integer changedBy, Date dateChanged, short voided, Integer voidedBy, Date dateVoided, String voidReason, String uuid, Set hrStaffNotes) {
        this.staffNoteId = staffNoteId;
        this.hrStaffNote = hrStaffNote;
        this.staffNoteType = staffNoteType;
        this.staffId = staffId;
        this.text = text;
        this.priority = priority;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
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

    public Integer getStaffId() {
        return this.staffId;
    }
    
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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

    public int getCreator() {
        return this.creator;
    }
    
    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getChangedBy() {
        return this.changedBy;
    }
    
    public void setChangedBy(Integer changedBy) {
        this.changedBy = changedBy;
    }

    public Date getDateChanged() {
        return this.dateChanged;
    }
    
    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public short getVoided() {
        return this.voided;
    }
    
    public void setVoided(short voided) {
        this.voided = voided;
    }

    public Integer getVoidedBy() {
        return this.voidedBy;
    }
    
    public void setVoidedBy(Integer voidedBy) {
        this.voidedBy = voidedBy;
    }

    public Date getDateVoided() {
        return this.dateVoided;
    }
    
    public void setDateVoided(Date dateVoided) {
        this.dateVoided = dateVoided;
    }

    public String getVoidReason() {
        return this.voidReason;
    }
    
    public void setVoidReason(String voidReason) {
        this.voidReason = voidReason;
    }

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set getHrStaffNotes() {
        return this.hrStaffNotes;
    }
    
    public void setHrStaffNotes(Set hrStaffNotes) {
        this.hrStaffNotes = hrStaffNotes;
    }
   








}
