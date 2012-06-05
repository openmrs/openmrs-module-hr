package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;

import java.util.List;

public interface HRPostHistoryDAO {
    public void savePostHistory(HrPostHistory postHistory);

    public void deletePostHistory(HrPostHistory postHistory);

    public List<HrPostHistory> findPostHistoryByExample(HrPostHistory postHistory);

    public HrPostHistory getPostHistoryById( int id);

    public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff);

	public HrPostHistory getCurrentPostForStaff(int staffId);
}
