package org.openmrs.module.hr.api;


import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffCert;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.Date;

import static org.junit.Assert.*;

public class HrQualificationServiceTest extends BaseModuleContextSensitiveTest{

    HRQualificationService hrQualificationService;
    @Before
    public void setUp() throws Exception {
        executeDataSet("person_test_data.xml");
        executeDataSet("staff_service_test_data.xml");
        executeDataSet("qualification_service_test_data.xml");
        hrQualificationService = Context.getService(HRQualificationService.class);
    }

    @Test
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(HRQualificationService.class));
    }

    @Test
    public void shouldGetCertificateById(){
        assertNotNull(hrQualificationService.getCertificateById(1));
    }

    @Test
    public void shouldRetireOrUnretireCertificate(){

        hrQualificationService.retireCertificate(hrQualificationService.getCertificateById(1), "test",Context.getAuthenticatedUser());
        assertTrue(hrQualificationService.getCertificateById(1).isRetired());
        hrQualificationService.unretireCertificate(hrQualificationService.getCertificateById(1));
        assertFalse(hrQualificationService.getCertificateById(1).isRetired());

    }

    @Test
    public void shouldGetAllCertificates(){
        assertEquals(2,hrQualificationService.getCertificates().size());
    }

    @Test
    public void shouldSaveCertificate(){
        HrCertificate certificate = new HrCertificate();
        certificate.setId(15);
        certificate.setAgency("agency");
        certificate.setCertificate("certificate");
        certificate.setLevels("levels");
        hrQualificationService.saveCertificate(certificate);
        assertNotNull(hrQualificationService.getCertificateById(15));

    }

    @Test
    public void shouldGetStaffCertById(){
        assertNotNull(hrQualificationService.getStaffCertificateById(1));
    }

    @Test
    public void shouldGetAllCertificatesForStaff(){
        HRStaffService hrStaffService = Context.getService(HRStaffService.class);
        HrStaff staff = hrStaffService.getStaffById(7777701);
        assertEquals(2,hrQualificationService.getCertificatesForStaff(staff).size());
    }

    @Test
    public void shouldSaveStaffCertificate(){
        HRStaffService hrStaffService = Context.getService(HRStaffService.class);
        HrStaffCert hrStaffCert = new HrStaffCert();
        hrStaffCert.setCurrentCertDate(new Date(1));
        hrStaffCert.setCertExpirationDate(new Date(2));
        hrStaffCert.setHrCertificate(hrQualificationService.getCertificateById(1));
        hrStaffCert.setHrStaff(hrStaffService.getStaffById(7777701));
        hrStaffCert.setImagePresent(true);
        hrStaffCert.setId(13);
        hrQualificationService.saveStaffCertificate(hrStaffCert);
        assertNotNull(hrQualificationService.getStaffCertificateById(13));
    }
}
