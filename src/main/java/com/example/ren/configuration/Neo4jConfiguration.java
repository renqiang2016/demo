package com.example.ren.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Neo4j配置类
 *
 * @author qiang.ren
 * @date 2018/4/17
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "com.example.ren.dao.neo4j")
public class Neo4jConfiguration{

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(configuration(), "com.example.ren.model.neo4j");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri("http://192.168.71.163:7474")
                .credentials("neo4j", "060012")
                .build();
    }
}
