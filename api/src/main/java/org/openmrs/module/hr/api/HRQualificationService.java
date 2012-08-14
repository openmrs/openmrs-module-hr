package org.openmrs.module.hr.api;


import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.module.hr.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HRQualificationService {

    @Authorized(PrivilegeConstants.MANAGE_CERTIFICATES)
    public HrCertificate getCertificateById(int id);

    @Authorized(PrivilegeConstants.MANAGE_CERTIFICATES)
    List<HrCertificate> getCertificates();

    @Authorized(PrivilegeConstants.MANAGE_CERTIFICATES)
    void retireCertificate(HrCertificate certificate, String retireReason , User retiredBy);

    @Authorized(PrivilegeConstants.MANAGE_CERTIFICATES)
    void saveCertificate(HrCertificate certificate);

    @Authorized(PrivilegeConstants.MANAGE_CERTIFICATES)
    void unretireCertificate(HrCertificate certificate);

    @Authorized(PrivilegeConstants.VIEW_CERTIFICATES)
    HrStaffCert getStaffCertificateById(Integer staffCertificateId);

    @Authorized(PrivilegeConstants.MANAGE_STAFF_CERTIFICATES)
    void saveStaffCertificate(HrStaffCert hrStaffCert);

    @Authorized(PrivilegeConstants.VIEW_EDUCATION)
    HrEducation getEducationById(Integer educationId);

    @Authorized(PrivilegeConstants.MANAGE_EDUCATION)
    void saveEducation(HrEducation education);

    @Authorized(PrivilegeConstants.VIEW_EDUCATION)
    List<HrEducation> getEducationsForStaff(HrStaff staff);

    @Authorized(PrivilegeConstants.MANAGE_STAFF_CERTIFICATES)
    void deleteStaffCertificate(HrStaffCert hrStaffCert);

    @Authorized(PrivilegeConstants.MANAGE_EDUCATION)
    void deleteEducation(HrEducation education);
}
