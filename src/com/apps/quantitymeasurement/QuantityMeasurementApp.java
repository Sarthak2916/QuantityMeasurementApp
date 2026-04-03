package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.util.ApplicationConfig;

import java.util.logging.Logger;

public class QuantityMeasurementApp {
    // Logger for logging information and errors in the Application class
    private static final Logger logger = Logger.getLogger(QuantityMeasurementApp.class.getName());

    private static QuantityMeasurementApp instance;
    private final QuantityMeasurementController controller;
    private final IQuantityMeasurementRepository repository;

    private QuantityMeasurementApp() {
        ApplicationConfig config = ApplicationConfig.getInstance();
        String repoType = config.getProperty("repository.type", "cache");

        if ("database".equalsIgnoreCase(repoType)) {
            this.repository = QuantityMeasurementDatabaseRepository.getInstance();
        } else {
            this.repository = QuantityMeasurementCacheRepository.getInstance();
        }

        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(this.repository);
        this.controller = new QuantityMeasurementController(service);

        logger.info("Quantity Measurement Application initialized with " + repoType + " repository");
    }

    public static synchronized QuantityMeasurementApp getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementApp();
        }
        return instance;
    }

    public QuantityMeasurementController getController() {
        return controller;
    }

    public IQuantityMeasurementRepository getRepository() {
        return repository;
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = getInstance();
        QuantityMeasurementController controller = app.getController();

        // Testing comparison of two quantities as per UC16 prompt
        QuantityDTO quantity1 = new QuantityDTO(2.0, QuantityDTO.LengthUnitDTO.FEET);
        QuantityDTO quantity2 = new QuantityDTO(24.0, QuantityDTO.LengthUnitDTO.INCHES);

        boolean comparisonResult = controller.performComparison(quantity1, quantity2);
        logger.info("Comparison result: " + comparisonResult);

        // Show repository stats
        logger.info("Total measurements in repository: " + app.getRepository().getTotalCount());
        logger.info("Pool Statistics: " + app.getRepository().getPoolStatistics());

        // Close resources
        app.getRepository().releaseResources();
    }
}