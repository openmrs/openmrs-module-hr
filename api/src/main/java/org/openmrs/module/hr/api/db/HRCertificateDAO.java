package org.openmrs.module.hr.api.db;

import org.openmrs.module.hr.HrCertificate;

import java.util.List;

public interface HRCertificateDAO {
    public void saveCertificate(HrCertificate certificate);

    public void deleteCertificate(HrCertificate certificate);

    public List<HrCertificate> getAllCertificates();

    public List<HrCertificate> findCertificatesByExample(HrCertificate certificate);

    public HrCertificate getCertificateById( int id);

}
