package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    // Logger for logging information and errors
    private static final Logger logger = Logger.getLogger(QuantityMeasurementCacheRepository.class.getName());

    // Private singleton instance
    private static QuantityMeasurementCacheRepository instance;

    // In-memory cache
    private final List<QuantityMeasurementEntity> cache;

    // Private constructor
    private QuantityMeasurementCacheRepository() {
        this.cache = new ArrayList<>();
        logger.info("QuantityMeasurementCacheRepository initialized.");
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public String save(QuantityMeasurementEntity entity) {
        if (entity.getId() == null) {
            entity.setId((long) (cache.size() + 1));
        }
        cache.add(entity);
        logger.info("Saved entity to cache: " + entity.getId());
        return String.valueOf(entity.getId());
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        List<QuantityMeasurementEntity> result = new ArrayList<>();
        for (QuantityMeasurementEntity entity : cache) {
            if (operation.equalsIgnoreCase(entity.getOperation())) {
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        List<QuantityMeasurementEntity> result = new ArrayList<>();
        for (QuantityMeasurementEntity entity : cache) {
            if (measurementType.equalsIgnoreCase(entity.getThisMeasurementType())) {
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public long getTotalCount() {
        return cache.size();
    }

    @Override
    public void deleteAll() {
        cache.clear();
        logger.info("Cache cleared.");
    }

    @Override
    public void releaseResources() {
        cache.clear();
        logger.info("Resources released.");
    }
}