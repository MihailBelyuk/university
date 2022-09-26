package com.solvd.university.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final String DRIVER = "driver";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String URL = "url";
    private static final String POOL_SIZE = "poolSize";
    private static ConnectionPool instance;
    private final ArrayBlockingQueue<Connection> connections;

    private ConnectionPool() {
        int poolSize = Integer.parseInt(PropertyReader.getValue(POOL_SIZE));
        try {
            Class.forName(PropertyReader.getValue(DRIVER));
        } catch (ClassNotFoundException e) {
            LOGGER.error("Failed o register driver.", e);
            throw new RuntimeException("Failed o register driver.", e);
        }
        connections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connections.add(createConnection());
        }
    }

    public Connection createConnection() {
        Connection connection;
        try {
            String user = PropertyReader.getValue(USER);
            String password = PropertyReader.getValue(PASSWORD);
            String url = PropertyReader.getValue(URL);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.error("Failed to get connection", e);
            throw new RuntimeException("Failed to get connection", e);
        }
        return connection;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.error("Failed to extract connection.", e);
            throw new RuntimeException("Failed to extract connection.", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Failed to put back connection.", e);
            throw new RuntimeException("Failed to put back connection.", e);
        }
    }
}
