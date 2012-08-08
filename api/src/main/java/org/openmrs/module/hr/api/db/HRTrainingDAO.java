package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrTraining;

import java.util.List;

public interface HRTrainingDAO {

    HrTraining saveTraining(HrTraining training);

    public void deleteTraining(HrTraining training);

    public List<HrTraining> findTrainingByExample(HrTraining training);

    public HrTraining getTrainingById( int id);

    public List<HrTraining> getTrainings();

    HrTraining getTrainingByUniqueId(String uuid);

    List<HrTraining> getTrainings(Boolean ifVoided, Integer index, Integer length);

    List<HrTraining> getTrainingsByCategory(String category, Integer index, Integer length);
}
