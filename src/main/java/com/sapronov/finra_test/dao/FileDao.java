package com.sapronov.finra_test.dao;

import com.sapronov.finra_test.models.SomeFile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by fa on 08/12/17.
 */
@Repository
public class FileDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.MANDATORY)
    public SomeFile createFileRecord(String name, String someProperty, Long fileSize) {
        SomeFile newSomeFile = new SomeFile();
        newSomeFile.setCreationTime(new Date());
        newSomeFile.setName(name);
        newSomeFile.setSomeProperty(someProperty);
        newSomeFile.setFileSize(fileSize);
        em.persist(newSomeFile);
        return newSomeFile;
    }

    public SomeFile getById(Long id) {
        return em.find(SomeFile.class, id);
    }

}
