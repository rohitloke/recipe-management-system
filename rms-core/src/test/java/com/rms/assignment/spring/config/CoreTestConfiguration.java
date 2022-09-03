package com.rms.assignment.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.rms.assignment.core.CoreConfig;

@Configuration
@Import(value = { CoreConfig.class })
public class CoreTestConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("rms")
                .addScript("create_db.ddl").build();
        return dataSource;
    }

}
