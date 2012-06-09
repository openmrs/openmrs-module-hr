package org.openmrs.module.hr.api.db;


import org.openmrs.module.hr.HrStaffCert;

import java.util.List;

public interface HRStaffCertDAO {

    public void saveStaffCert(HrStaffCert staffCert);

    public void deleteStaffCert(HrStaffCert staffCert);

    public List<HrStaffCert> findStaffCertByExample(HrStaffCert staffCert);

    public HrStaffCert getStaffCertById( int id);

}
