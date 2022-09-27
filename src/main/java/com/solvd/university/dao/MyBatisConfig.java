package com.solvd.university.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisConfig {

    private static final Logger LOGGER = LogManager.getLogger(MyBatisConfig.class);

    private static final String CONFIG_PATH = "mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(CONFIG_PATH);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            LOGGER.error("Failed to read " + CONFIG_PATH);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
