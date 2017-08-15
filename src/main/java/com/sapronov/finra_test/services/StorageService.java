package com.sapronov.finra_test.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by fa on 08/14/17.
 */
public interface StorageService {

    void saveFile(Long id, byte[] data) throws IOException;

    byte[] getFile(long id) throws IOException;
}
