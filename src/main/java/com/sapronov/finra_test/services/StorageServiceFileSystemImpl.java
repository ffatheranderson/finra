package com.sapronov.finra_test.services;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * Created by fa on 08/14/17.
 */
@Service
public class StorageServiceFileSystemImpl implements StorageService {

    private static String fileStorage = "c:/storage";

    @PostConstruct
    public void init() {
        createStorageDirectory();
    }

    private void createStorageDirectory() {
        File storage = new File(fileStorage);
        if (!storage.exists())
            storage.mkdir();
    }


    @Override
    public void saveFile(Long id, byte[] data) throws IOException {
        File newFile = new File(fileStorage + File.separator + id);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))) {
            bos.write(data);
        }
    }

    @Override
    public byte[] getFile(long id) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileStorage + File.separator + String.valueOf(id)))) {
            return IOUtils.toByteArray(bis);
        }
    }
}
