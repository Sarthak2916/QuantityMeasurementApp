package com.apps.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * ConnectionPool class manages a pool of database connections for efficient reuse.
 */
public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance;
    private List<Connection> availableConnections;
    private List<Connection> usedConnections;
    private final int poolSize;
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private final String driverClass;
    private final String testQuery;

    private ConnectionPool() throws SQLException {
        ApplicationConfig config = ApplicationConfig.getInstance();
        this.poolSize = config.getIntProperty(ApplicationConfig.ConfigKey.DB_POOL_SIZE.getKey(), 10);
        this.dbUrl = config.getProperty(ApplicationConfig.ConfigKey.DB_URL.getKey());
        this.dbUsername = config.getProperty(ApplicationConfig.ConfigKey.DB_USERNAME.getKey());
        this.dbPassword = config.getProperty(ApplicationConfig.ConfigKey.DB_PASSWORD.getKey());
        this.driverClass = config.getProperty(ApplicationConfig.ConfigKey.DB_DRIVER_CLASS.getKey());
        this.testQuery = config.getProperty(ApplicationConfig.ConfigKey.HIKARI_CONNECTION_TEST_QUERY.getKey(), "SELECT 1");

        availableConnections = new ArrayList<>(poolSize);
        usedConnections = new ArrayList<>();

        initializeConnections();
    }

    public static synchronized ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void initializeConnections() throws SQLException {
        try {
            Class.forName(driverClass);
            for (int i = 0; i < poolSize; i++) {
                availableConnections.add(createConnection());
            }
            logger.info("Connection pool initialized with " + poolSize + " connections");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found: " + driverClass, e);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    public synchronized Connection getConnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            throw new SQLException("No available connections in the pool");
        }

        Connection connection = availableConnections.remove(availableConnections.size() - 1);

        if (!validateConnection(connection)) {
            connection = createConnection();
        }

        usedConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            usedConnections.remove(connection);
            availableConnections.add(connection);
        }
    }

    public boolean validateConnection(Connection connection) {
        try (var stmt = connection.createStatement()) {
            stmt.execute(this.testQuery);
            return true;
        } catch (SQLException e) {
            logger.warning("Connection validation failed: " + e.getMessage());
            return false;
        }
    }

    public synchronized void closeAll() {
        for (Connection c : availableConnections) {
            try { c.close(); } catch (SQLException e) { /* ignore */ }
        }
        for (Connection c : usedConnections) {
            try { c.close(); } catch (SQLException e) { /* ignore */ }
        }
        availableConnections.clear();
        usedConnections.clear();
        logger.info("All connections in the pool have been closed.");
    }

    public int getAvailableConnectionCount() {
        return availableConnections.size();
    }

    public int getUsedConnectionCount() {
        return usedConnections.size();
    }

    public int getTotalConnectionCount() {
        return availableConnections.size() + usedConnections.size();
    }

    @Override
    public String toString() {
        return "ConnectionPool {Available: " + getAvailableConnectionCount() +
                ", Used: " + getUsedConnectionCount() + "}";
    }

    public static void main(String[] args) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn1 = pool.getConnection();
            logger.info("Validate connection: " + (pool.validateConnection(conn1) ? "Success" : "Failure"));
            logger.info("Available connections after acquiring 1: " + pool.getAvailableConnectionCount());
            pool.releaseConnection(conn1);
            logger.info("Available connections after releasing 1: " + pool.getAvailableConnectionCount());
            pool.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}