package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffNote;

import java.util.List;

public interface HRStaffNoteDAO {

    public void saveStaffNote(HrStaffNote staffNote);

    public void deleteStaffNote(HrStaffNote staffNote);

    public List<HrStaffNote> findStaffNoteByExample(HrStaffNote staffCert);

    public List<HrStaffNote> getAllStaffNotes();

    public HrStaffNote getStaffNoteById(int id);

    List<HrStaffNote> getAllStaffNotesForStaff(HrStaff staff, String injury);

    List<HrStaffNote> getHeadStaffNotesForStaff(HrStaff staff, String injury);

    List<HrStaffNote> getAllChildrenNotes(HrStaffNote noteId);
}
