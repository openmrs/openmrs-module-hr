package org.openmrs.module.hr.api;


import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffCert;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRQualificationService {

    @Authorized("Manage Certificates")
    public HrCertificate getCertificateById(int id);

    @Authorized("Manage Certificates")
    List<HrCertificate> getCertificates();

    @Authorized("Manage Certificates")
    void retireCertificate(HrCertificate certificate, String retireReason , User retiredBy);

    @Authorized("Manage Certificates")
    void saveCertificate(HrCertificate certificate);

    @Authorized("Manage Certificates")
    void unretireCertificate(HrCertificate certificate);

    @Authorized("View Certificates")//2724101101983
    HrStaffCert getStaffCertificateById(Integer staffCertificateId);

    @Authorized("View Certificates")
    List<HrStaffCert> getCertificatesForStaff(HrStaff staff);

    @Authorized("Add Staff Certificates")
    void saveStaffCertificate(HrStaffCert hrStaffCert);
}
