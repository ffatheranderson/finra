package com.sapronov.finra_test.comfig;

import com.sapronov.finra_test.dao.FileDao;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fa on 08/14/17.
 */
@Configuration
public class TestContext {

    @Bean
    public FileDao fileDao() {
        return Mockito.mock(FileDao.class);
    }



}
