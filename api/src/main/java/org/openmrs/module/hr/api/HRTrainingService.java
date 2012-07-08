package org.openmrs.module.hr.api;

import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRTrainingService {
    @Authorized("Manage Trainings")
    HrTraining getTrainingById(Integer trainingId);

    @Authorized("Manage Trainings")
    List<HrTraining> getTrainings();

    @Authorized("Manage Trainings")
    void saveTraining(HrTraining training);

    @Authorized("Manage Trainings")
    void unretireTraining(HrTraining trainingById);

    @Authorized("Manage Trainings")
    void retireTraining(HrTraining trainingById, String retireReason, User authenticatedUser);


    @Authorized("Manage TrainingClasses")
    List<HrTrainingClass> getTrainingClasses();

    @Authorized("Manage TrainingClasses")
    void saveTrainingClass(HrTrainingClass hrTrainingClass);

    @Authorized("Manage TrainingClasses")
    void deleteTrainingClasses(HrTrainingClass trainingClass);

    @Authorized("Manage TrainingClasses")
    HrTrainingClass getTrainingClassById(Integer trainingClassId);
}
