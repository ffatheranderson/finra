package com.sapronov.finra_test.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sapronov.finra_test.dao.FileDao;
import com.sapronov.finra_test.models.SomeFile;
import com.sapronov.finra_test.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fa on 08/12/17.
 */
@Controller
@RequestMapping("/files")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FileController {

    @Autowired
    private FileDao fileDao;

    @Autowired
    private StorageService storageService;

    public static final String CONTENT_TYPE = "application/octet-stream";
    public static final String CONTENT_DISPOSITION_HEADER_NAME = "Content-Disposition";

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam String name,
                             @RequestParam String someProperty,
                             @RequestParam MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new RuntimeException("SomeFile must to be presented.");

        SomeFile someFileRow = fileDao.createFileRecord(name, someProperty, file.getSize());
        storageService.saveFile(someFileRow.getId(), file.getBytes());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("id", someFileRow.getId());

        return mapper.writeValueAsString(root);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getFileMetadata(@RequestParam Long id) throws JsonProcessingException {
        SomeFile file = fileDao.getById(id);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("id", file.getId());
        root.put("name", file.getName());
        root.put("someProperty", file.getSomeProperty());
        root.put("creationTime", file.getCreationTime().toString());
        root.put("size", file.getFileSize());

        return mapper.writeValueAsString(root);
    }

    @RequestMapping("/download")
    public void downloadFile(@RequestParam Long id, HttpServletResponse resp) throws IOException {
        SomeFile file = fileDao.getById(id);
        resp.setHeader(CONTENT_DISPOSITION_HEADER_NAME, "attachment; filename=" + file.getName());
        resp.setContentType(CONTENT_TYPE);
        resp.setContentLengthLong(file.getFileSize());
        resp.getOutputStream().write(storageService.getFile(id));
    }


}
