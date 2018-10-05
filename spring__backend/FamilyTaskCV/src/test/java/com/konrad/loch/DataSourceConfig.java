package com.konrad.loch;

import javax.sql.DataSource;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.konrad.loch.repositories.FamilyRepository;
import com.konrad.loch.repositories.FamilyRepositoryImpl;
import com.konrad.loch.utils.AppUtils;

@TestConfiguration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
    	System.out.println("H2 db config....");
        return new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }
}