package org.openmrs.module.hr.api;

import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.PrivilegeConstants;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRCompetencyService {
    @Authorized(PrivilegeConstants.VIEW_COMPETENCIES)
    HrCompetency getCompetencyById(Integer competencyId);

    @Authorized(PrivilegeConstants.VIEW_COMPETENCIES)
    List<HrCompetency> getCompetencies();

    @Authorized(PrivilegeConstants.MANAGE_COMPETENCIES)
    void saveCompetency(HrCompetency competency);

    @Authorized(PrivilegeConstants.MANAGE_COMPETENCIES)
    void unretireCompetency(HrCompetency competency);

    @Authorized(PrivilegeConstants.MANAGE_COMPETENCIES)
    void retireCompetency(HrCompetency competencyById, String retireReason, User authenticatedUser);

    @Authorized(PrivilegeConstants.VIEW_EVALUATIONS)
    HrEvaluation getEvaluationById(Integer evaluationId);

    @Authorized(PrivilegeConstants.MANAGE_EVALUATIONS)
    void saveEvaluation(HrEvaluation hrEvaluation);

    @Authorized(PrivilegeConstants.MANAGE_EVALUATIONS)
    void deleteEvaluation(HrEvaluation hrEvaluation);
}
