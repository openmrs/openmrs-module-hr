package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffNote;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRNoteService {

    @Authorized("Manage Notes")
    void saveNote(HrStaffNote note);

    @Authorized("View Notes")
    HrStaffNote getStaffNoteById(Integer noteId);

    @Authorized("Manage Notes")
    void deleteStaffNote(HrStaffNote staffNoteById);

    @Authorized("View Notes")
    List<HrStaffNote> getHeadNotesForStaff(HrStaff staff, String injury);
}
