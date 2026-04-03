package com.apps.quantitymeasurement.test.integrationTests;

import com.apps.quantitymeasurement.QuantityMeasurementApp;
import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementApp app;
    private QuantityMeasurementController controller;
    private IQuantityMeasurementRepository repository;

    @BeforeEach
    public void setUp() {
        System.setProperty("app.env", "test");
        app = QuantityMeasurementApp.getInstance();
        controller = app.getController();
        repository = app.getRepository();
        repository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        repository.releaseResources();
    }

    @Test
    public void testComparisonIntegration() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnitDTO.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnitDTO.INCHES);

        boolean result = controller.performComparison(q1, q2);
        assertTrue(result);

        List<QuantityMeasurementEntity> history = repository.getAllMeasurements();
        assertFalse(history.isEmpty());

        boolean found = history.stream()
                .anyMatch(e -> "COMPARE".equals(e.getOperation()));

        assertTrue(found, "COMPARE operation not found in history");
    }

    @Test
    public void testAdditionIntegration() {
        QuantityDTO q1 = new QuantityDTO(2.0, QuantityDTO.LengthUnitDTO.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnitDTO.INCHES);

        QuantityDTO result = controller.performAddition(q1, q2, q1); // Target FEET
        assertEquals(3.0, result.getValue(), 0.01);

        List<QuantityMeasurementEntity> history = repository.getAllMeasurements();

        boolean found = history.stream()
                .anyMatch(e -> "ADD".equals(e.getOperation()) &&
                        Math.abs(e.getResultValue() - 3.0) < 0.01);

        assertTrue(found, "ADD operation with result 3.0 not found in history");
    }
}