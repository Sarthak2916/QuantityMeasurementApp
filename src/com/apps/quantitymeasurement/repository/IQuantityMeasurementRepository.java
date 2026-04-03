package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {
    String save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> getAllMeasurements();
    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operationType);
    List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType);
    long getTotalCount();
    void deleteAll();

    default String getPoolStatistics() {
        return "Not using a connection pool";
    }

    default void releaseResources() {
        // Default implementation does nothing
    }
}