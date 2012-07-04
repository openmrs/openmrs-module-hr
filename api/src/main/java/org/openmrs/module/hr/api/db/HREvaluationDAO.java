package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrStaff;

import java.util.List;

public interface HREvaluationDAO {

    public void saveEvaluation(HrEvaluation evaluation);

    public void deleteEvaluation(HrEvaluation evaluation);

    public List<HrEvaluation> getEvaluationsForStaffAndCompetency(HrStaff staff,HrCompetency competency);

    public List<HrEvaluation> findEvaluationByExample(HrEvaluation evaluation);

    public HrEvaluation getEvaluationById( int id);

    List<HrEvaluation> getEvaluationsForStaff(HrStaff staff);
}
