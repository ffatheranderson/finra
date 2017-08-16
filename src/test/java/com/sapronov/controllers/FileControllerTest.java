package com.sapronov.controllers;

import com.sapronov.finra_test.comfig.FileControllerTestContext;
import com.sapronov.finra_test.config.AppConfig;
import com.sapronov.finra_test.controllers.FileController;
import com.sapronov.finra_test.dao.FileDao;
import com.sapronov.finra_test.models.SomeFile;
import com.sapronov.finra_test.services.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

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
    public void testUploadFileSuccess() throws Exception {
        String fileName = "someFile.txt";
        Long id = 1L;

        SomeFile retFile = new SomeFile();
        retFile.setId(id);

        when(fileDaoMock.createFileRecord(any(), any(), any())).thenReturn(retFile);
        MvcResult mvcResult = mockMvc.perform(fileUpload("/files").file("file", new byte[]{1, 2, 3})
                .param("name", fileName).param("someProperty", "propVall"))
                .andExpect(status().isOk()).andReturn();
        verify(fileDaoMock, times(1)).createFileRecord(any(), any(), any());
        verify(storageServiceMock, times(1)).saveFile(any(), any());
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"id\":" + id + "}", contentAsString);
    }

    @Test
    public void testGetFileMetadata() throws Exception {
        SomeFile retFile = new SomeFile();
        Long id = 1L;
        retFile.setId(id);
        String name = "fileName.txt";
        retFile.setName(name);
        Long fileSize = 123L;
        retFile.setFileSize(fileSize);
        Date creationTime = new Date();
        retFile.setCreationTime(creationTime);
        String someProperty = "someValueee";
        retFile.setSomeProperty(someProperty);

        when(fileDaoMock.getById(id)).thenReturn(retFile);

        MvcResult mvcResult = mockMvc.perform(get("/files?id=" + id)).andExpect(status().isOk()).andReturn();
        verify(fileDaoMock, times(1)).getById(id);

        String resultString = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"id\":" + id
                + ",\"name\":\"" + name
                + "\",\"someProperty\":\""
                + someProperty + "\",\"creationTime\":\""
                + creationTime.toString() + "\",\"size\":"
                + fileSize + "}", resultString);
    }

    @Test
    public void testDownloadFile() throws Exception {
        Long id = 1L;

        byte[] fileData = new byte[]{1, 2, 4, 5, 1, 2, 3, 41, 1, 43, 4, 1, 23, 34, 35, 43, 5};
        Long size = (long) fileData.length;
        SomeFile retFile = new SomeFile();
        retFile.setFileSize(size);

        when(fileDaoMock.getById(id)).thenReturn(retFile);
        when(storageServiceMock.getFile(id)).thenReturn(fileData);

        MvcResult mvcResult = mockMvc.perform(get("/files/download?id=1")).andExpect(status().isOk()).andReturn();

        verify(fileDaoMock, times(1)).getById(id);
        verify(storageServiceMock, times(1)).getFile(id);

        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(FileController.CONTENT_TYPE, response.getContentType());
        assertTrue(response.getHeaderNames().contains(FileController.CONTENT_DISPOSITION_HEADER_NAME));
        assertEquals(size, new Long(response.getContentLengthLong()));
    }

}
