-- Quantity Measurement Database Schema

CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
                                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                           this_value DOUBLE PRECISION,
                                                           this_unit VARCHAR(50),
    this_measurement_type VARCHAR(50),
    that_value DOUBLE PRECISION,
    that_unit VARCHAR(50),
    that_measurement_type VARCHAR(50),
    operation VARCHAR(50),
    result_value DOUBLE PRECISION,
    result_unit VARCHAR(50),
    result_measurement_type VARCHAR(50),
    result_string VARCHAR(255),
    is_error BOOLEAN DEFAULT FALSE,
    error_message VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );