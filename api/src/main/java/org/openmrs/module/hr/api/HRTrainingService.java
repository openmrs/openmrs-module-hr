package org.openmrs.module.hr.api;

import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRTrainingService extends OpenmrsService{
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

    @Authorized("Manage TrainingClasses")
    HrTrainingClass getTrainingClassByUniqueId(String uuid);

    @Authorized("Manage Trainings")
    void deleteTraining(HrTraining hrTraining);

    @Authorized("Manage Trainings")
    HrTraining getTrainingByUniqueId(String uuid);

    List<HrTrainPerson> getTrainingHistoryFor(int personId);

    List<HrTraining> getTrainings(Boolean ifRetired, Integer index, Integer length);

    int getCountOfTrainings(Boolean ifRetired);

    void saveTrainPerson(HrTrainPerson hrTrainPerson);

    void deleteTrainPerson(HrTrainPerson hrTrainPerson);

    HrTrainPerson getTrainPersonByUniqueID(String uuid);
}
