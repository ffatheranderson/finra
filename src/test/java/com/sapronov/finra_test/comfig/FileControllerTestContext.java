package com.sapronov.finra_test.comfig;

import com.sapronov.finra_test.dao.FileDao;
import com.sapronov.finra_test.services.StorageService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * Created by fa on 08/14/17.
 */
@Configuration
@ComponentScan({"com.sapronov.finra_test.controllers"})
public class FileControllerTestContext {

    @Bean
    public EntityManagerFactory emf() {
        return Mockito.mock(EntityManagerFactory.class);
    }

    @Bean
    public FileDao fileDaoMock() {
        return Mockito.mock(FileDao.class);
    }

    @Bean
    public StorageService storageMock() {
        return Mockito.mock(StorageService.class);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();

        properties.setProperty("server.http.port", "80");

        pspc.setProperties(properties);
        return pspc;
    }

}
