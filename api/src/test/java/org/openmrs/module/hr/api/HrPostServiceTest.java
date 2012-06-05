package org.openmrs.module.hr.api;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.SkipBaseSetup;
import org.openmrs.test.TestUtil;
import org.openmrs.util.RoleConstants;

import javax.management.relation.Role;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class HrPostServiceTest extends BaseModuleContextSensitiveTest {
    HRPostService hrPostService;

    @Before
	public void before() throws Exception {
        executeDataSet("person_test_data.xml");
        executeDataSet("privilege_data.xml");
        executeDataSet("post_service_test_data.xml");
        executeDataSet("staff_service_test_data.xml");
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

    @Test
    @SkipBaseSetup
    public void shouldGetPostHistoryForStaff() throws ClassNotFoundException, SQLException, DatabaseUnitException, IOException {
        Context.authenticate("hrmanager","Hrmanager123");
        assertNotNull(hrPostService.getPostHistoriesForStaff(Context.getService(HRStaffService.class).getStaffById(7777701)));
        Context.authenticate("admin","Admin123");
//        Connection jdbcConnection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/openmrs", "root", "password");
//        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
//
//        // partial database export
//        QueryDataSet partialDataSet = new QueryDataSet(connection);
//        partialDataSet.addTable("hr_post_history");
//        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

    }
    @Test
    @SkipBaseSetup
    public void shouldGetPostHistoryById(){
        Context.authenticate("hrmanager","Hrmanager123");
        assertNotNull(hrPostService.getPostHistoryById(7777701));
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldGetCurrentPostForStaff(){
        Context.authenticate("hrmanager","Hrmanager123");
        HrPostHistory hrPostHistory = hrPostService.getCurrentPostForStaff(7777701);
        assertEquals((Integer)7777701,hrPostHistory.getHrStaff().getId());
        Context.authenticate("admin","Admin123");
    }

    @Test
    @SkipBaseSetup
    public void shouldSavePostHistory(){
        Context.authenticate("hrmanager","Hrmanager123");
        HrPostHistory hrPostHistory = hrPostService.getPostHistoryById(7777701);
        hrPostHistory.setGrade("Grade Changed");
        hrPostService.savePostHistory(hrPostHistory);
        assertEquals("Grade Changed",hrPostService.getPostHistoryById(7777701).getGrade());
        Context.authenticate("admin","Admin123");
    }
}


