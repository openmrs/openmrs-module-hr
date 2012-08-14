package org.openmrs.module.hr.api;

import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hr.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRTrainingService extends OpenmrsService{
    @Authorized(PrivilegeConstants.MANAGE_TRAININGS)
    HrTraining getTrainingById(Integer trainingId);

    @Authorized(PrivilegeConstants.MANAGE_TRAININGS)
    List<HrTraining> getTrainings();

    @Authorized(PrivilegeConstants.MANAGE_TRAININGS)
    HrTraining saveTraining(HrTraining training);

    @Authorized(PrivilegeConstants.MANAGE_TRAININGS)
    void unretireTraining(HrTraining trainingById);

    @Authorized(PrivilegeConstants.MANAGE_TRAININGS)
    void retireTraining(HrTraining trainingById, String retireReason, User authenticatedUser);

    @Authorized(PrivilegeConstants.MANAGE_TRAINING_CLASSES)
    List<HrTrainingClass> getTrainingClasses();

    @Authorized(PrivilegeConstants.MANAGE_TRAINING_CLASSES)
    HrTrainingClass saveTrainingClass(HrTrainingClass hrTrainingClass);

    @Authorized(PrivilegeConstants.MANAGE_TRAINING_CLASSES)
    void deleteTrainingClasses(HrTrainingClass trainingClass);

    @Authorized(PrivilegeConstants.MANAGE_TRAINING_CLASSES)
    HrTrainingClass getTrainingClassById(Integer trainingClassId);

    @Authorized(PrivilegeConstants.MANAGE_TRAINING_CLASSES)
    HrTrainingClass getTrainingClassByUniqueId(String uuid);

    @Authorized(PrivilegeConstants.MANAGE_TRAININGS)
    void deleteTraining(HrTraining hrTraining);

    @Authorized(PrivilegeConstants.MANAGE_TRAININGS)
    HrTraining getTrainingByUniqueId(String uuid);

    @Authorized(PrivilegeConstants.VIEW_STAFF_TRAININGS)
    List<HrTrainPerson> getTrainingHistoryFor(int personId);

    @Authorized(PrivilegeConstants.VIEW_STAFF_TRAININGS)
    List<HrTraining> getTrainings(Boolean ifRetired, Integer index, Integer length);

    @Authorized({PrivilegeConstants.VIEW_STAFF_TRAININGS,PrivilegeConstants.VIEW_TRAININGS})
    List<HrTraining> getTrainingsByCategory(String category,Integer index,Integer length);

    @Authorized({PrivilegeConstants.VIEW_STAFF_TRAININGS,PrivilegeConstants.VIEW_TRAININGS})
    int getCountOfTrainingsByCategory(String category);

    @Authorized({PrivilegeConstants.VIEW_STAFF_TRAININGS,PrivilegeConstants.VIEW_TRAININGS})
    int getCountOfTrainings(Boolean ifRetired);

    @Authorized(PrivilegeConstants.MANAGE_STAFF_TRAININGS)
    HrTrainPerson saveTrainPerson(HrTrainPerson hrTrainPerson);

    @Authorized(PrivilegeConstants.MANAGE_STAFF_TRAININGS)
    void deleteTrainPerson(HrTrainPerson hrTrainPerson);

    @Authorized(PrivilegeConstants.MANAGE_STAFF_TRAININGS)
    HrTrainPerson getTrainPersonByUniqueID(String uuid);
}
