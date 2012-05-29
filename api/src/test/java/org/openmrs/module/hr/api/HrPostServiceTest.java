package org.openmrs.module.hr.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrPost;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.junit.Assert.*;

public class HrPostServiceTest extends BaseModuleContextSensitiveTest {
    HRPostService hrPostService;

    @Before
	public void before() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("post_service_test_data.xml");
        hrPostService = Context.getService(HRPostService.class);
	}

    @Test
    public void shouldSetUpContext(){
        assertNotNull(Context.getService(HRPostService.class));
    }

    @Test
    public void shouldGetAllISCOCodes(){
        assertEquals(2,hrPostService.getAllIscoCodes().size());
    }

    @Test
    public void shouldGetISCOOcdeById(){
        assertNotNull(hrPostService.getIscoCodeById("2"));
    }

    @Test
    public void shouldGetJobTitleById(){
        assertNotNull(hrPostService.getJobTitleById(2));
    }

    @Test
    public void shouldRetireOrUnRetireJobTitle(){
        HrJobTitle hrJobTitle = hrPostService.getJobTitleById(2);
        hrPostService.retireJobTitle(hrJobTitle,"test reason");
        assertTrue(hrPostService.getJobTitleById(2).isRetired());
        hrPostService.unretireJobTitle(hrJobTitle);
        assertFalse(hrPostService.getJobTitleById(2).isRetired());
    }

    @Test
    public void shouldGetAllJobTitles(){
        assertEquals(2,hrPostService.getAllJobTitles().size());
    }

    @Test
    public void shouldSaveJobTitle(){
        HrJobTitle hrJobTitle = hrPostService.getJobTitleById(1);
        hrJobTitle.setTitle("changed title");
        hrPostService.saveJobTitle(hrJobTitle);
        assertEquals("changed title", hrPostService.getJobTitleById(1).getTitle());
    }

    @Test
    public void shouldGetAllPosts(){
        assertEquals(2, hrPostService.getAllPosts(true, true).size());
    }

    @Test
    public void shouldGetPostById(){
        assertNotNull(hrPostService.getPostById(1));
    }

    @Test
    public void shouldRetireOrUnRetirePost(){
        HrPost hrPost = hrPostService.getPostById(2);
        hrPostService.retirePost(hrPost,"test reason");
        assertTrue(hrPostService.getPostById(2).isRetired());
        hrPostService.unretirePost(hrPost);
        assertFalse(hrPostService.getPostById(2).isRetired());
    }

    @Test
    public void shouldSavePost(){
        HrPost hrPost = hrPostService.getPostById(2);
        hrPost.setFundingSource("MHRD");
        hrPostService.savePost(hrPost);
        assertEquals("MHRD",hrPostService.getPostById(2).getFundingSource());
    }


}


