package org.openmrs.module.hr.api.impl;

import org.openmrs.User;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.api.HRCompetencyService;
import org.openmrs.module.hr.api.db.HRCompetencyDAO;
import org.openmrs.module.hr.api.db.HREvaluationDAO;

import java.util.List;

public class HRCompetencyServiceImpl implements HRCompetencyService {


    HRCompetencyDAO hrCompetencyDAO;
    HREvaluationDAO hrEvaluationDAO;


    public void setHrCompetencyDAO(HRCompetencyDAO hrCompetencyDAO) {
        this.hrCompetencyDAO = hrCompetencyDAO;
    }

    public void setHrEvaluationDAO(HREvaluationDAO hrEvaluationDAO) {
        this.hrEvaluationDAO = hrEvaluationDAO;
    }

    @Override
    public HrCompetency getCompetencyById(Integer competencyId) {
        return hrCompetencyDAO.getCompetencyById(competencyId);
    }

    @Override
    public List<HrCompetency> getCompetencies() {
        return hrCompetencyDAO.getAllCompetencies();
    }

    @Override
    public void saveCompetency(HrCompetency competency) {
        hrCompetencyDAO.saveCompetency(competency);
    }

    @Override
    public void unretireCompetency(HrCompetency competency) {
        competency.setRetired(false);
        hrCompetencyDAO.saveCompetency(competency);
    }

    @Override
    public void retireCompetency(HrCompetency competency, String retireReason, User authenticatedUser) {
        competency.setRetired(true);
        competency.setRetiredBy(authenticatedUser);
        competency.setRetireReason(retireReason);
        hrCompetencyDAO.saveCompetency(competency);
    }

    @Override
    public HrEvaluation getEvaluationById(Integer evaluationId) {
        return hrEvaluationDAO.getEvaluationById(evaluationId);
    }

    @Override
    public void saveEvaluation(HrEvaluation hrEvaluation) {
        hrEvaluationDAO.saveEvaluation(hrEvaluation);
    }

    @Override
    public void deleteEvaluation(HrEvaluation hrEvaluation) {
        hrEvaluationDAO.deleteEvaluation(hrEvaluation);
    }

}
