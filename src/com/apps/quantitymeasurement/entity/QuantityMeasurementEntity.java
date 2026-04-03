package com.apps.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double thisValue;
    private String thisUnit;
    private String thisMeasurementType;
    private Double thatValue;
    private String thatUnit;
    private String thatMeasurementType;
    private String operation;
    private Double resultValue;
    private String resultUnit;
    private String resultMeasurementType;
    private String resultString;
    private boolean isError;
    private String errorMessage;

    public QuantityMeasurementEntity() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getThisValue() { return thisValue; }
    public void setThisValue(Double thisValue) { this.thisValue = thisValue; }

    public String getThisUnit() { return thisUnit; }
    public void setThisUnit(String thisUnit) { this.thisUnit = thisUnit; }

    public String getThisMeasurementType() { return thisMeasurementType; }
    public void setThisMeasurementType(String thisMeasurementType) { this.thisMeasurementType = thisMeasurementType; }

    public Double getThatValue() { return thatValue; }
    public void setThatValue(Double thatValue) { this.thatValue = thatValue; }

    public String getThatUnit() { return thatUnit; }
    public void setThatUnit(String thatUnit) { this.thatUnit = thatUnit; }

    public String getThatMeasurementType() { return thatMeasurementType; }
    public void setThatMeasurementType(String thatMeasurementType) { this.thatMeasurementType = thatMeasurementType; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public Double getResultValue() { return resultValue; }
    public void setResultValue(Double resultValue) { this.resultValue = resultValue; }

    public String getResultUnit() { return resultUnit; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }

    public String getResultMeasurementType() { return resultMeasurementType; }
    public void setResultMeasurementType(String resultMeasurementType) { this.resultMeasurementType = resultMeasurementType; }

    public String getResultString() { return resultString; }
    public void setResultString(String resultString) { this.resultString = resultString; }

    public boolean isError() { return isError; }
    public void setError(boolean error) { isError = error; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", result='" + resultString + '\'' +
                '}';
    }
}