package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;

public class HrStaffNote extends BaseNote {


	private static final long serialVersionUID = 1L;
    private HrStaff hrStaff;
    private HrStaffNote parent;
    private Set<HrStaffNote> hrStaffNotes = new HashSet<HrStaffNote>(0);



    // Constructors

    /** default constructor */
    public HrStaffNote() {
    }

    public Set<HrStaffNote> getHrStaffNotes() {
        return hrStaffNotes;
    }

    public void setHrStaffNotes(Set<HrStaffNote> hrStaffNotes) {
        this.hrStaffNotes = hrStaffNotes;
    }

    public HrStaffNote getParent() {
        return parent;
    }

    public void setParent(HrStaffNote parent) {
        this.parent = parent;
    }


    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

}
