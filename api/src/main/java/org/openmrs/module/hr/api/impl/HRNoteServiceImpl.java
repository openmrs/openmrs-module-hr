package org.openmrs.module.hr.api.impl;

import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.api.HRNoteService;
import org.openmrs.module.hr.api.db.HRStaffNoteDAO;

import java.util.List;

public class HRNoteServiceImpl implements HRNoteService {

    HRStaffNoteDAO hrStaffNoteDAO;

    public void setHrStaffNoteDAO(HRStaffNoteDAO hrStaffNoteDAO) {
        this.hrStaffNoteDAO = hrStaffNoteDAO;
    }


    @Override
    public void saveNote(HrStaffNote note) {
        hrStaffNoteDAO.saveStaffNote(note);
    }

    @Override
    public List<HrStaffNote> getHeadNotesForStaff(HrStaff staff, String injury) {
        return hrStaffNoteDAO.getHeadStaffNotesForStaff(staff, injury);
    }

    @Override
    public HrStaffNote getStaffNoteById(Integer noteId) {
        return hrStaffNoteDAO.getStaffNoteById(noteId);
    }

    @Override
    public void deleteInjury(HrStaffNote hrStaffNote) {
        hrStaffNoteDAO.deleteStaffNote(hrStaffNote);
    }
}
