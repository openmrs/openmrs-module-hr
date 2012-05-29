package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrIscoCodes;

import java.util.List;

public interface HRISCOCodeDAO {
    public List<HrIscoCodes> getAllIscoCodes();

	public HrIscoCodes getIscoCodeById( String id) ;
}
