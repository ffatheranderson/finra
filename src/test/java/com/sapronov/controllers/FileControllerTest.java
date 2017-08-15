package com.sapronov.controllers;

import com.sapronov.finra_test.comfig.FileControllerTestContext;
import com.sapronov.finra_test.config.AppConfig;
import com.sapronov.finra_test.dao.FileDao;
import com.sapronov.finra_test.services.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by fa on 08/14/17.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FileControllerTestContext.class, AppConfig.class})
public class FileControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private FileDao fileDaoMock;

    @Autowired
    private StorageService storageServiceMock;

    @Before
    public void setUp() {
        Mockito.reset(fileDaoMock);
        Mockito.reset(storageServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void testUploadFile() throws Exception {
        String fileName = "someFile.txt";
        MockMultipartFile mpf = new MockMultipartFile(fileName, new byte[]{});
        ResultActions result = mockMvc.perform(fileUpload("/files").file("file", new byte[]{1, 2, 3})
                .param("name", fileName).param("someProperty", "propVall"));
        System.out.println();
    }

    @Test
    public void testGetFileMetadata() throws Exception {
        ResultActions perform = mockMvc.perform(get("/files?id=1"));
        System.out.println();
    }

}
