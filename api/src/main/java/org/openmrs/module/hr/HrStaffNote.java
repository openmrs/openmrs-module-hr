package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;


public class HrStaffNote extends BaseNote {


	private static final long serialVersionUID = 1L;



    private int hrStaffNoteId;
    private HrStaff hrStaff;
    private Set<HrStaffNote> hrStaffNotes = new HashSet<HrStaffNote>(0);

    private HrStaffNote parent;


    // Constructors

    /** default constructor */
    public HrStaffNote() {
    }

	/** minimal constructor */
    public HrStaffNote(int staffNoteId, String text, String uuid) {
        super(text,uuid);
        this.hrStaffNoteId = staffNoteId;
    }
    
    /** full constructor */
    public HrStaffNote(int staffNoteId, HrStaff hrStaff, HrStaffNote parent, String staffNoteType, String text, Integer priority, String uuid, Set<HrStaffNote> hrStaffNotes) {
        super(staffNoteType,text,priority,uuid);
        this.hrStaffNoteId = staffNoteId;
        this.parent = parent;
        this.hrStaffNotes = hrStaffNotes;
        this.hrStaff = hrStaff;
    }


    public HrStaffNote getParent() {
        return parent;
    }

    public void setParent(HrStaffNote parent) {
        this.parent = parent;
    }

    public Set<HrStaffNote> getHrStaffNotes() {
        return hrStaffNotes;
    }

    public void setHrStaffNotes(Set<HrStaffNote> hrStaffNotes) {
        this.hrStaffNotes = hrStaffNotes;
    }
    public int getHrStaffNoteId() {
        return hrStaffNoteId;
    }

    public void setHrStaffNoteId(int hrStaffNoteId) {
        this.hrStaffNoteId = hrStaffNoteId;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    @Override
    public Integer getId() {
        return hrStaffNoteId;
    }

    @Override
    public void setId(Integer integer) {
        this.hrStaffNoteId = integer;
    }
}
