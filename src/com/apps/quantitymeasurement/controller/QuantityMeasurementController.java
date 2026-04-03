package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import java.util.logging.Logger;

public class QuantityMeasurementController {
    // Logger for logging information and errors in the controller
    private static final Logger logger = Logger.getLogger(QuantityMeasurementController.class.getName());
    private final IQuantityMeasurementService quantityMeasurementService;

    public QuantityMeasurementController(IQuantityMeasurementService quantityMeasurementService) {
        this.quantityMeasurementService = quantityMeasurementService;
        logger.info("QuantityMeasurementController initialized with service: " + quantityMeasurementService);
    }

    public boolean performComparison(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        logger.info("Controller: Comparison request received for " + thisQuantityDTO.getUnit() + " and " + thatQuantityDTO.getUnit());
        return quantityMeasurementService.compare(thisQuantityDTO, thatQuantityDTO);
    }

    public QuantityDTO performConversion(QuantityDTO thisQuantityDTO, QuantityDTO targetUnitDTO) {
        logger.info("Controller: Conversion request received for " + thisQuantityDTO.getUnit() + " to " + targetUnitDTO.getUnit());
        return quantityMeasurementService.convert(thisQuantityDTO, targetUnitDTO);
    }

    public QuantityDTO performAddition(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        logger.info("Controller: Addition request received");
        return quantityMeasurementService.add(thisQuantityDTO, thatQuantityDTO);
    }

    public QuantityDTO performAddition(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
        logger.info("Controller: Addition (with target) request received");
        return quantityMeasurementService.add(thisQuantityDTO, thatQuantityDTO, targetUnitDTO);
    }

    public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        logger.info("Controller: Subtraction request received");
        return quantityMeasurementService.subtract(thisQuantityDTO, thatQuantityDTO);
    }

    public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
        logger.info("Controller: Subtraction (with target) request received");
        return quantityMeasurementService.subtract(thisQuantityDTO, thatQuantityDTO, targetUnitDTO);
    }

    public double performDivision(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        logger.info("Controller: Division request received");
        return quantityMeasurementService.divide(thisQuantityDTO, thatQuantityDTO);
    }
}