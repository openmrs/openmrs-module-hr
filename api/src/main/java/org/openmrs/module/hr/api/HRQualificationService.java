package org.openmrs.module.hr.api;


import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.HrCertificate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRQualificationService {

    @Authorized("Manage Certificates")
    public HrCertificate getCertificateById(int id);

    @Authorized("Manage Certificates")
    List<HrCertificate> getCertificates();

    void retireCertificate(HrCertificate certificate, String retireReason);

    void saveCertificate(HrCertificate certificate);

    void unretireCertificate(HrCertificate certificate);
}
