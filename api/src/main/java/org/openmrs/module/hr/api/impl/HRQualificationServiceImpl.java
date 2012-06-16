package org.openmrs.module.hr.api.impl;


import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.api.HRQualificationService;
import org.openmrs.module.hr.api.db.HRCertificateDAO;

import java.util.List;

public class HRQualificationServiceImpl implements HRQualificationService{

    HRCertificateDAO hrCertificateDAO;

    public void setHrCertificateDAO(HRCertificateDAO hrCertificateDAO) {
        this.hrCertificateDAO = hrCertificateDAO;
    }

    public HrCertificate getCertificateById(int id) {
        return hrCertificateDAO.getCertificateById(id);
    }


    public List<HrCertificate> getCertificates() {
        return hrCertificateDAO.getAllCertificates();
    }

    public void retireCertificate(HrCertificate certificate, String retireReason) {
         hrCertificateDAO.saveCertificate(certificate);
    }

    public void saveCertificate(HrCertificate certificate) {
        hrCertificateDAO.saveCertificate(certificate);
    }

    public void unretireCertificate(HrCertificate certificate) {
        hrCertificateDAO.saveCertificate(certificate);

    }
}
