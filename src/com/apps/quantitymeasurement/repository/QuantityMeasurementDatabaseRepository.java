package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {
    // Logger for logging database operations and errors
    private static final Logger logger = Logger.getLogger(QuantityMeasurementDatabaseRepository.class.getName());

    // Singleton instance of the repository
    private static QuantityMeasurementDatabaseRepository instance;

    private static final String INSERT_QUERY =
            "INSERT INTO quantity_measurement_entity " +
                    "(this_value, this_unit, this_measurement_type, that_value, that_unit, " +
                    "that_measurement_type, operation, result_value, result_unit, " +
                    "result_measurement_type, result_string, is_error, error_message, " +
                    "created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM quantity_measurement_entity ORDER BY created_at DESC";

    private static final String SELECT_BY_OPERATION =
            "SELECT * FROM quantity_measurement_entity WHERE operation = ? ORDER BY created_at DESC";

    private static final String SELECT_BY_MEASUREMENT_TYPE =
            "SELECT * FROM quantity_measurement_entity " +
                    "WHERE this_measurement_type = ? ORDER BY created_at DESC";

    private static final String DELETE_ALL_QUERY =
            "DELETE FROM quantity_measurement_entity";

    private static final String COUNT_QUERY =
            "SELECT COUNT(*) FROM quantity_measurement_entity";

    private ConnectionPool connectionPool;

    public QuantityMeasurementDatabaseRepository() {
        try {
            this.connectionPool = ConnectionPool.getInstance();
            initializeDatabase();
            logger.info("QuantityMeasurementDatabaseRepository initialized successfully.");
        } catch (SQLException e) {
            logger.severe("Failed to initialize database repository: " + e.getMessage());
            throw new DatabaseException("Failed to initialize database repository", e);
        }
    }

    public static synchronized QuantityMeasurementDatabaseRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementDatabaseRepository();
        }
        return instance;
    }

    private void initializeDatabase() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS quantity_measurement_entity (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "this_value DOUBLE PRECISION," +
                "this_unit VARCHAR(50)," +
                "this_measurement_type VARCHAR(50)," +
                "that_value DOUBLE PRECISION," +
                "that_unit VARCHAR(50)," +
                "that_measurement_type VARCHAR(50)," +
                "operation VARCHAR(50)," +
                "result_value DOUBLE PRECISION," +
                "result_unit VARCHAR(50)," +
                "result_measurement_type VARCHAR(50)," +
                "result_string VARCHAR(255)," +
                "is_error BOOLEAN DEFAULT FALSE," +
                "error_message VARCHAR(255)," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        try (Connection conn = connectionPool.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
            logger.info("Database initialized successfully.");
        } catch (SQLException e) {
            logger.severe("Error initializing database: " + e.getMessage());
        }
    }

    @Override
    public String save(QuantityMeasurementEntity entity) {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setObject(1, entity.getThisValue());
            pstmt.setString(2, entity.getThisUnit());
            pstmt.setString(3, entity.getThisMeasurementType());
            pstmt.setObject(4, entity.getThatValue());
            pstmt.setString(5, entity.getThatUnit());
            pstmt.setString(6, entity.getThatMeasurementType());
            pstmt.setString(7, entity.getOperation());
            pstmt.setObject(8, entity.getResultValue());
            pstmt.setString(9, entity.getResultUnit());
            pstmt.setString(10, entity.getResultMeasurementType());
            pstmt.setString(11, entity.getResultString());
            pstmt.setBoolean(12, entity.isError());
            pstmt.setString(13, entity.getErrorMessage());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    String id = String.valueOf(rs.getLong(1));
                    logger.info("QuantityMeasurementEntity saved with ID: " + id);
                    return id;
                }
            }
            return null;
        } catch (SQLException e) {
            logger.severe("Error saving entity: " + e.getMessage());
            throw new DatabaseException("Error saving entity", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        List<QuantityMeasurementEntity> measurements = new ArrayList<>();
        try (Connection conn = connectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY)) {
            while (rs.next()) {
                measurements.add(mapResultSetToEntity(rs));
            }
            logger.info("Retrieved " + measurements.size() + " measurements.");
            return measurements;
        } catch (SQLException e) {
            logger.severe("Error retrieving all measurements: " + e.getMessage());
            throw new DatabaseException("Error retrieving all measurements", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        List<QuantityMeasurementEntity> measurements = new ArrayList<>();
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_OPERATION)) {
            pstmt.setString(1, operation);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    measurements.add(mapResultSetToEntity(rs));
                }
            }
            return measurements;
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving by operation", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        List<QuantityMeasurementEntity> measurements = new ArrayList<>();
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_MEASUREMENT_TYPE)) {
            pstmt.setString(1, measurementType);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    measurements.add(mapResultSetToEntity(rs));
                }
            }
            return measurements;
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving by type", e);
        }
    }

    @Override
    public long getTotalCount() {
        try (Connection conn = connectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(COUNT_QUERY)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error counting measurements", e);
        }
    }

    @Override
    public void deleteAll() {
        try (Connection conn = connectionPool.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(DELETE_ALL_QUERY);
            logger.info("All measurements deleted.");
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting measurements", e);
        }
    }

    @Override
    public String getPoolStatistics() {
        return connectionPool.toString();
    }

    @Override
    public void releaseResources() {
        connectionPool.closeAll();
    }

    private QuantityMeasurementEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        // Since the current entity class might use different fields,
        // I will need to verify the QuantityMeasurementEntity.java fields.
        // Assuming the entity has getters and setters matching the DB schema.
        entity.setThisValue(rs.getObject("this_value", Double.class));
        entity.setThisUnit(rs.getString("this_unit"));
        entity.setThisMeasurementType(rs.getString("this_measurement_type"));
        entity.setThatValue(rs.getObject("that_value", Double.class));
        entity.setThatUnit(rs.getString("that_unit"));
        entity.setThatMeasurementType(rs.getString("that_measurement_type"));
        entity.setOperation(rs.getString("operation"));
        entity.setResultValue(rs.getObject("result_value", Double.class));
        entity.setResultUnit(rs.getString("result_unit"));
        entity.setResultMeasurementType(rs.getString("result_measurement_type"));
        entity.setResultString(rs.getString("result_string"));
        entity.setError(rs.getBoolean("is_error"));
        entity.setErrorMessage(rs.getString("error_message"));
        return entity;
    }
}