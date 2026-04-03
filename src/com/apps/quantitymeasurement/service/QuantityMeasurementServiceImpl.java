package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.entity.QuantityModel;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.unit.*;
import java.util.logging.Logger;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    // Logger for logging information and errors
    private static final Logger logger = Logger.getLogger(QuantityMeasurementServiceImpl.class.getName());
    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
        logger.info("QuantityMeasurementServiceImpl init with repository: " + repository.getClass().getSimpleName());
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        QuantityModel<IMeasurable> m1 = toModel(q1);
        QuantityModel<IMeasurable> m2 = toModel(q2);
        validateSameType(m1, m2);

        boolean result = Double.compare(m1.getUnit().toBase(m1.getValue()),
                m2.getUnit().toBase(m2.getValue())) == 0;

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        populateBaseEntity(entity, m1, m2, "COMPARE");
        entity.setResultString(result ? "Equal" : "Not Equal");
        entity.setResultValue(result ? 1.0 : 0.0);

        repository.save(entity);
        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO q1, QuantityDTO targetDTO) {
        QuantityModel<IMeasurable> m1 = toModel(q1);
        QuantityModel<IMeasurable> target = toModel(targetDTO);
        validateSameType(m1, target);

        double baseValue = m1.getUnit().toBase(m1.getValue());
        double resultValue = target.getUnit().fromBase(baseValue);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        populateBaseEntity(entity, m1, null, "CONVERT");
        entity.setResultValue(resultValue);
        entity.setResultUnit(target.getUnit().getUnitName());
        entity.setResultMeasurementType(target.getUnit().getMeasurementType());
        entity.setResultString(String.valueOf(resultValue));

        repository.save(entity);

        return new QuantityDTO(resultValue, (QuantityDTO.IMeasurableUnit) findUnitInDTO(targetDTO));
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return add(q1, q2, q1);
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetDTO) {
        return performArithmetic(q1, q2, targetDTO, "ADD");
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return subtract(q1, q2, q1);
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetDTO) {
        return performArithmetic(q1, q2, targetDTO, "SUBTRACT");
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        QuantityModel<IMeasurable> m1 = toModel(q1);
        QuantityModel<IMeasurable> m2 = toModel(q2);
        validateSameType(m1, m2);

        double base1 = m1.getUnit().toBase(m1.getValue());
        double base2 = m2.getUnit().toBase(m2.getValue());

        if (base2 == 0) throw new ArithmeticException("Division by zero");

        double result = base1 / base2;

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        populateBaseEntity(entity, m1, m2, "DIVIDE");
        entity.setResultValue(result);
        entity.setResultString(String.valueOf(result));

        repository.save(entity);

        return result;
    }

    private void populateBaseEntity(QuantityMeasurementEntity entity, QuantityModel<IMeasurable> m1, QuantityModel<IMeasurable> m2, String op) {
        entity.setThisValue(m1.getValue());
        entity.setThisUnit(m1.getUnit().getUnitName());
        entity.setThisMeasurementType(m1.getUnit().getMeasurementType());
        entity.setOperation(op);
        if (m2 != null) {
            entity.setThatValue(m2.getValue());
            entity.setThatUnit(m2.getUnit().getUnitName());
            entity.setThatMeasurementType(m2.getUnit().getMeasurementType());
        }
    }

    private QuantityModel<IMeasurable> toModel(QuantityDTO dto) {
        if (dto == null) return null;
        IMeasurable unit;
        try {
            switch (dto.getMeasurementType()) {
                case "LENGTH": unit = LengthUnit.valueOf(dto.getUnit()); break;
                case "WEIGHT": unit = WeightUnit.valueOf(dto.getUnit()); break;
                case "VOLUME": unit = VolumeUnit.valueOf(dto.getUnit()); break;
                case "TEMPERATURE": unit = TemperatureUnit.valueOf(dto.getUnit()); break;
                default: throw new QuantityMeasurementException("Invalid Category: " + dto.getMeasurementType());
            }
            return new QuantityModel<>(dto.getValue(), unit);
        } catch (IllegalArgumentException e) {
            throw new QuantityMeasurementException("Invalid Unit: " + dto.getUnit());
        }
    }

    private void validateSameType(QuantityModel<IMeasurable> m1, QuantityModel<IMeasurable> m2) {
        if (m1 == null || m2 == null) return;
        if (!m1.getUnit().getMeasurementType().equals(m2.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Incompatible types: " +
                    m1.getUnit().getMeasurementType() + " vs " + m2.getUnit().getMeasurementType());
        }
    }

    private QuantityDTO performArithmetic(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetDTO, String op) {
        QuantityModel<IMeasurable> m1 = toModel(q1);
        QuantityModel<IMeasurable> m2 = toModel(q2);
        QuantityModel<IMeasurable> target = toModel(targetDTO);

        validateSameType(m1, m2);
        validateSameType(m1, target);

        m1.getUnit().validateOperationSupport(op);
        m2.getUnit().validateOperationSupport(op);

        double v1 = m1.getUnit().toBase(m1.getValue());
        double v2 = m2.getUnit().toBase(m2.getValue());

        double resBase = op.equals("ADD") ? v1 + v2 : v1 - v2;
        double resValue = target.getUnit().fromBase(resBase);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        populateBaseEntity(entity, m1, m2, op);
        entity.setResultValue(resValue);
        entity.setResultUnit(target.getUnit().getUnitName());
        entity.setResultMeasurementType(target.getUnit().getMeasurementType());
        entity.setResultString(String.valueOf(resValue));

        repository.save(entity);

        return new QuantityDTO(resValue, (QuantityDTO.IMeasurableUnit) findUnitInDTO(targetDTO));
    }

    private Object findUnitInDTO(QuantityDTO dto) {
        String type = dto.getMeasurementType();
        String name = dto.getUnit();
        if ("LENGTH".equals(type)) return QuantityDTO.LengthUnitDTO.valueOf(name);
        if ("WEIGHT".equals(type)) return QuantityDTO.WeightUnitDTO.valueOf(name);
        if ("VOLUME".equals(type)) return QuantityDTO.VolumeUnitDTO.valueOf(name);
        if ("TEMPERATURE".equals(type)) return QuantityDTO.TemperatureUnitDTO.valueOf(name);
        return null;
    }
}