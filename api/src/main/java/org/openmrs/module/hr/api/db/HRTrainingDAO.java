package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrTraining;

import java.util.List;

public interface HRTrainingDAO {

    public void saveTraining(HrTraining training);

    public void deleteTraining(HrTraining training);

    public List<HrTraining> findTrainingByExample(HrTraining training);

    public HrTraining getTrainingById( int id);

    public List<HrTraining> getTrainings();

    HrTraining getTrainingByUniqueId(String uuid);
}
