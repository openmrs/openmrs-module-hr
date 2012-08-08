package org.openmrs.module.hr.api.db;

import org.openmrs.Person;
import org.openmrs.module.hr.HrTrainPerson;

import java.util.List;

public interface HRTrainPersonDAO {

    public HrTrainPerson saveTrainPerson(HrTrainPerson trainPerson);

    public void deleteTrainPerson(HrTrainPerson trainPerson);

    public List<HrTrainPerson> findTrainPersonByExample(HrTrainPerson trainPerson);

    public HrTrainPerson getTrainPersonById( int id);

    List<HrTrainPerson> getTrainingHistoryFor(Person person);

    HrTrainPerson getTrainPersonByUniqueID(String uuid);
}
