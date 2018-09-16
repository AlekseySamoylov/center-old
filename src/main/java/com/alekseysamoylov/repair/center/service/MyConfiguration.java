package com.alekseysamoylov.repair.center.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by alekseysamoylov on 7/7/16.
 * Getting id for user role
 */
@Service
public class MyConfiguration {
    private final Logger LOGGER = Logger.getLogger(MyConfiguration.class);

    public Long getClientRoleId() {
        return getProperty("clientRoleId");
    }

    public Long getMasterRoleId() {
        return getProperty("masterRoleId");
    }

    public Long getManagerRoleId() { return getProperty("managerRoleId"); }

    private Long getProperty(String propertyName) {
        Long id = null;
        try (InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            Properties properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
                id = Long.valueOf(properties.getProperty(propertyName));
            } else {
                LOGGER.warn("Exception from MyConfiguration  ");
//                throw new FileNotFoundException(propertyName + "property file not found in the classpath");
            }

        } catch (IOException ex) {
            LOGGER.warn("Exception: " + ex);
        }
        return id;
    }
}

