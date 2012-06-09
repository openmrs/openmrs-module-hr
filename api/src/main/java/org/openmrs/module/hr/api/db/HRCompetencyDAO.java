package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrCompetency;

import java.util.List;

public interface HRCompetencyDAO {
    public void saveCompetency(HrCompetency competency);

    public void deleteCompetency(HrCompetency competency) ;

    public List<HrCompetency> getAllCompetencies();

    public List<HrCompetency> findCompetencyByExample(HrCompetency competency);

    public HrCompetency getCompetencyById( int id);

}
