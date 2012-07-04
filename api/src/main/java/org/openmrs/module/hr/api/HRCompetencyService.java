package org.openmrs.module.hr.api;

import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrStaff;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRCompetencyService {
    @Authorized("View Competencies")
    HrCompetency getCompetencyById(Integer competencyId);

    @Authorized("View Competencies")
    List<HrCompetency> getCompetencies();

    @Authorized("Manage Competencies")
    void saveCompetency(HrCompetency competency);

    @Authorized("Manage Competencies")
    void unretireCompetency(HrCompetency competency);

    @Authorized("Manage Competencies")
    void retireCompetency(HrCompetency competencyById, String retireReason, User authenticatedUser);

    @Authorized("View Evaluation")
    HrEvaluation getEvaluationById(Integer evaluationId);

    @Authorized("View Evaluation")
    List<HrEvaluation> getEvaluationsForStaff(HrStaff staff);

    @Authorized("Manage Evaluation")
    void saveEvaluation(HrEvaluation hrEvaluation);
}
