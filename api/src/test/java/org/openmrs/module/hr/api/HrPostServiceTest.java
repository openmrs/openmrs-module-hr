package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.SkipBaseSetup;
import org.openmrs.test.TestUtil;
import org.openmrs.util.RoleConstants;

import javax.management.relation.Role;

import static org.junit.Assert.*;

public class HrPostServiceTest extends BaseModuleContextSensitiveTest {
    HRPostService hrPostService;

    @Before
	public void before() throws Exception {
        executeDataSet("person_test_data.xml");
        executeDataSet("privilege_data.xml");
        executeDataSet("post_service_test_data.xml");
        hrPostService = Context.getService(HRPostService.class);
	}

    @Test
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(HRPostService.class));
    }

    @Test
    @SkipBaseSetup
    public void shouldGetAllISCOCodes() throws Exception {
        Context.authenticate("hrmanager","Hrmanager123");
        assertEquals(619, hrPostService.getAllIscoCodes().size());
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldGetISCOOcdeById(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertNotNull(hrPostService.getIscoCodeById("1"));
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldGetJobTitleById(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertNotNull(hrPostService.getJobTitleById(1));
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldRetireOrUnRetireJobTitle(){
        Context.authenticate("hrmanager","Hrmanager123");
        HrJobTitle hrJobTitle = hrPostService.getJobTitleById(1);
        hrPostService.retireJobTitle(hrJobTitle,"test reason");
        assertTrue(hrPostService.getJobTitleById(1).isRetired());
        hrPostService.unretireJobTitle(hrJobTitle);
        assertFalse(hrPostService.getJobTitleById(1).isRetired());
        Context.authenticate("admin","Admin123");

    }

    @Test
    @SkipBaseSetup
    public void shouldGetAllJobTitles(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertEquals(31,hrPostService.getAllJobTitles().size());
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldSaveJobTitle(){
        Context.authenticate("hrmanager","Hrmanager123");
        HrJobTitle hrJobTitle = hrPostService.getJobTitleById(1);
        hrJobTitle.setTitle("changed title");
        hrPostService.saveJobTitle(hrJobTitle);
        assertEquals("changed title", hrPostService.getJobTitleById(1).getTitle());
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldGetAllPosts(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertEquals(91, hrPostService.getAllPosts(true, true).size());
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldGetPostById(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertNotNull(hrPostService.getPostById(7777701));
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldRetireOrUnRetirePost(){
        Context.authenticate("hrmanager","Hrmanager123");
        HrPost hrPost = hrPostService.getPostById(7777701);
        hrPostService.retirePost(hrPost,"test reason");
        assertTrue(hrPostService.getPostById(7777701).isRetired());
        hrPostService.unretirePost(hrPost);
        assertFalse(hrPostService.getPostById(7777701).isRetired());
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldSavePost(){
        Context.authenticate("hrmanager","Hrmanager123");
        HrPost hrPost = hrPostService.getPostById(7777701);
        hrPost.setFundingSource("MHRD");
        hrPostService.savePost(hrPost);
        assertEquals("MHRD",hrPostService.getPostById(7777701).getFundingSource());
        Context.authenticate("admin","Admin123");
    }


}


