package org.openmrs.module.hr.api.impl
        ;

import org.openmrs.User;
import org.openmrs.api.db.PersonDAO;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.hr.api.db.HRTrainPersonDAO;
import org.openmrs.module.hr.api.db.HRTrainingClassDAO;
import org.openmrs.module.hr.api.db.HRTrainingDAO;

import java.util.List;

public class HRTrainingServiceImpl implements HRTrainingService {


    HRTrainingDAO hrTrainingDAO;
    HRTrainingClassDAO hrTrainingClassDAO;
    HRTrainPersonDAO hrTrainPersonDAO;
    PersonDAO personDAO;

    public void setPersonDAO(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public void setHrTrainPersonDAO(HRTrainPersonDAO hrTrainPersonDAO) {
        this.hrTrainPersonDAO = hrTrainPersonDAO;
    }

    public void setHrTrainingClassDAO(HRTrainingClassDAO hrTrainingClassDAO) {
        this.hrTrainingClassDAO = hrTrainingClassDAO;
    }

    public void setHrTrainingDAO(HRTrainingDAO hrTrainingDAO) {
        this.hrTrainingDAO = hrTrainingDAO;
    }

    @Override
    public HrTraining getTrainingById(Integer trainingId) {
        return hrTrainingDAO.getTrainingById(trainingId);
    }

    @Override
    public List<HrTraining> getTrainings() {
        return hrTrainingDAO.getTrainings();
    }

    @Override
    public void saveTraining(HrTraining training) {
        hrTrainingDAO.saveTraining(training);
    }

    @Override
    public void unretireTraining(HrTraining training) {
        training.setRetired(false);
        hrTrainingDAO.saveTraining(training);
    }

    @Override
    public void retireTraining(HrTraining hrTraining, String retireReason, User authenticatedUser) {
        hrTraining.setRetired(true);
        hrTraining.setRetiredBy(authenticatedUser);
        hrTraining.setRetireReason(retireReason);
        hrTrainingDAO.saveTraining(hrTraining);
    }

    @Override
    public List<HrTrainingClass> getTrainingClasses() {
        return hrTrainingClassDAO.getTariningClasses();
    }

    @Override
    public void saveTrainingClass(HrTrainingClass hrTrainingClass) {
        hrTrainingClassDAO.saveTrainingClass(hrTrainingClass);
    }

    @Override
    public void deleteTrainingClasses(HrTrainingClass trainingClass) {
        hrTrainingClassDAO.deleteTrainingClass(trainingClass);
    }

    @Override
    public HrTrainingClass getTrainingClassById(Integer trainingClassId) {
       return hrTrainingClassDAO.getTrainingClassById(trainingClassId);
    }

    @Override
    public HrTrainingClass getTrainingClassByUniqueId(String uuid) {
        return hrTrainingClassDAO.getTrainingClassByUniqueId(uuid);
    }

    @Override
    public void deleteTraining(HrTraining hrTraining) {
        hrTrainingDAO.deleteTraining(hrTraining);
    }

    @Override
    public HrTraining getTrainingByUniqueId(String uuid) {
        return hrTrainingDAO.getTrainingByUniqueId(uuid);
    }

    @Override
    public List<HrTrainPerson> getTrainingHistoryFor(int personId) {
        return hrTrainPersonDAO.getTrainingHistoryFor(personDAO.getPerson(personId));
    }
}
