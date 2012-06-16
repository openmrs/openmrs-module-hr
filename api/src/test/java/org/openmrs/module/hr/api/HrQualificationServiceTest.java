package org.openmrs.module.hr.api;


import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.*;

public class HrQualificationServiceTest extends BaseModuleContextSensitiveTest{

    HRQualificationService hrQualificationService;
    @Before
    public void setUp() throws Exception {
        executeDataSet("person_test_data.xml");
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
        hrQualificationService.retireCertificate(hrQualificationService.getCertificateById(1), "test");
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


}
