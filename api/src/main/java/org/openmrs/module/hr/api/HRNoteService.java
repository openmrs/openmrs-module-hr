package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.BaseNote;
import org.openmrs.module.hr.HrInjury;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffNote;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRNoteService {

    @Authorized("Manage Notes")
    void saveNote(HrStaffNote note);

    @Authorized("View Notes")
    List<HrStaffNote> getHeadNotesForStaff(HrStaff staff, String injury);

    @Authorized("View Notes")
    HrStaffNote getStaffNoteById(Integer noteId);

    @Authorized("Manage Notes")
    void deleteInjury(HrStaffNote staffNoteById);
}
