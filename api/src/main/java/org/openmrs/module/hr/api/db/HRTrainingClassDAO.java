package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrTrainingClass;

import java.util.List;

public interface HRTrainingClassDAO {

    public HrTrainingClass saveTrainingClass(HrTrainingClass trainingClass);

    public void deleteTrainingClass(HrTrainingClass trainingClass);

    public List<HrTrainingClass> findTrainingClassByExample(HrTrainingClass trainingClass) ;

    public HrTrainingClass getTrainingClassById( int id);

    List<HrTrainingClass> getTariningClasses();

    HrTrainingClass getTrainingClassByUniqueId(String uuid);
}
