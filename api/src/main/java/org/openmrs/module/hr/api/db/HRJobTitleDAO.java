package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrJobTitle;

import java.util.List;

public interface HRJobTitleDAO {

    public void deleteJobTitle(HrJobTitle jobTitle);

    public void saveJobTitle(HrJobTitle jobTitle);

	public List<HrJobTitle> getAllJobTitles();

	public List<HrJobTitle> findJobTitleByExample(HrJobTitle jobTitle);

	public HrJobTitle getJobTitleById( int id);

}
