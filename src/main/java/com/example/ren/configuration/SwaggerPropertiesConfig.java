package com.example.ren.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * swagger
 *
 * @author qiang.ren on 2018/3/26
 */
@Configuration
public class SwaggerPropertiesConfig {

    @Value("${swagger.title}")
    private String swaggerTitle;

    @Value("${swagger.login.name}")
    private String swaggerLoginName;

    @Value("${swagger.login.password}")
    private String swaggerLoginPasswd;

    @Value("${spring.profiles.active}")
    private String environment;

    public String getSwaggerTitle() {
        return swaggerTitle;
    }

    public void setSwaggerTitle(String swaggerTitle) {
        this.swaggerTitle = swaggerTitle;
    }

    public String getSwaggerLoginName() {
        return swaggerLoginName;
    }

    public void setSwaggerLoginName(String swaggerLoginName) {
        this.swaggerLoginName = swaggerLoginName;
    }

    public String getSwaggerLoginPasswd() {
        return swaggerLoginPasswd;
    }

    public void setSwaggerLoginPasswd(String swaggerLoginPasswd) {
        this.swaggerLoginPasswd = swaggerLoginPasswd;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
