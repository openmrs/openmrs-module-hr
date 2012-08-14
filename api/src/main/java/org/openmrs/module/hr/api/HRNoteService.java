package org.openmrs.module.hr.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.PrivilegeConstants;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRNoteService {

    @Authorized(PrivilegeConstants.MANAGE_NOTES)
    void saveNote(HrStaffNote note);

    @Authorized(PrivilegeConstants.VIEW_NOTES)
    HrStaffNote getStaffNoteById(Integer noteId);

    @Authorized(PrivilegeConstants.MANAGE_NOTES)
    void deleteStaffNote(HrStaffNote staffNoteById);

    @Authorized(PrivilegeConstants.VIEW_NOTES)
    List<HrStaffNote> getHeadNotesForStaff(HrStaff staff, String injury);
}
