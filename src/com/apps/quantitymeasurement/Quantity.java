package com.apps.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

public class Quantity <U extends IMeasurable>{

    private double value;
    private U unit;
    private double convertedValue;

    private enum ArithmeticOperation{
        ADD((a,b)->a+b),
        SUBTRACT((a,b)->a-b),
        DIVIDE((a,b)->{
            if(b==0) throw new ArithmeticException("Divide by Zero");
            return a/b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation){
            this.operation= operation;
        }

        public double compute(double a, double b){
            return operation.applyAsDouble(a,b);
        }
    }

    public Quantity(double value, U unit) throws IllegalArgumentException{
        this.value= value;
        if(unit==null) throw new IllegalArgumentException("Not a valid unit");
        this.unit= unit;
        this.convertedValue= convertToBaseUnit();
    }
    public U getUnit(){
        return unit;
    }
    private double convertToBaseUnit(){
        return unit.convertToBaseUnit(value);
    }
    public boolean compare(Quantity<U> length){
        return Double.compare(this.convertedValue, length.convertedValue)==0 ? true:false;
    }
    @Override
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null || !(obj instanceof Quantity<?>)) return false;

        Quantity<U> other= (Quantity<U>) obj;
        if(!other.unit.getClass().equals(this.unit.getClass())) return false;
        return compare(other);
    }

    public <U extends IMeasurable> Quantity<U> convertTo(U targetUnit) throws IllegalArgumentException{
        if(targetUnit==null) throw new IllegalArgumentException("Not a valid Argument");
        double val= convertFromBaseToTargetUnit(convertedValue, targetUnit);
        return new Quantity(val, targetUnit);
    }

    public Quantity<U> add(Quantity<U> quantity){
        return addAndConvert(quantity, this.unit);
    }
    public Quantity<U> add(Quantity<U> quantity, U targetUnit){
        return addAndConvert(quantity, targetUnit);
    }
    private Quantity<U> addAndConvert(Quantity<U> quantity, U targetUnit){
        validateArithmeticOperands(quantity, targetUnit);
        double value= performArithmetic(quantity,targetUnit,ArithmeticOperation.ADD);
        return new Quantity<>(value, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> quantity){
        return subtractAndConvert(quantity, this.unit);
    }
    public Quantity<U> subtract(Quantity<U> quantity, U targetUnit){
        return subtractAndConvert(quantity, targetUnit);
    }
    private Quantity<U> subtractAndConvert(Quantity<U> quantity, U targetUnit){
        validateArithmeticOperands(quantity, targetUnit);
        double value= performArithmetic(quantity,targetUnit,ArithmeticOperation.SUBTRACT);
        return new Quantity<>(value, targetUnit);
    }

    public Quantity<U> divide(Quantity<U> quantity){
        return divideAndConvert(quantity, this.unit);
    }
    public  Quantity<U> divide(Quantity<U> quantity, U targetUnit){
        return divideAndConvert(quantity, targetUnit);
    }
    private Quantity<U> divideAndConvert(Quantity<U> quantity, U targetUnit){
        validateArithmeticOperands(quantity, targetUnit);
        double value= performArithmetic(quantity,targetUnit,ArithmeticOperation.DIVIDE);
        return new Quantity<>(value, targetUnit);
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit) throws IllegalArgumentException{
        if(other==null) throw new IllegalArgumentException("Object is null");
        if(targetUnit==null) throw new IllegalArgumentException("targetUnit is null");
        if(!Double.isFinite(other.value) || !Double.isFinite(other.value)) throw new IllegalArgumentException("Invalid Numeric Value");
        if(this.unit.getClass()!=targetUnit.getClass() || other.unit.getClass()!=targetUnit.getClass() || this.unit.getClass()!=other.unit.getClass() )
            throw new IllegalArgumentException("Incompatible unit type");
    }
    private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation){
        double value= operation.compute(this.convertedValue,other.convertedValue);
        return convertFromBaseToTargetUnit(value,targetUnit);
    }


    private <U extends IMeasurable> double convertFromBaseToTargetUnit(double baseValue, U targetUnit){
        return unit.convertFromBaseToTargetUnit(baseValue, targetUnit);
    }

    public String toString(){
        return value+" "+unit;
    }

    public static void main(String[] args) {

        Quantity q1= new Quantity(27,LengthUnit.FEET);
        Quantity q2= new Quantity(9,LengthUnit.YARDS);
        System.out.println("Are lengths equal: "+q1.equals(q2));

        Quantity len1= new Quantity(5, LengthUnit.FEET);
        Quantity converted= len1.convertTo(LengthUnit.INCHES);
        System.out.println(converted.toString());

        System.out.println(q2.add(q1, LengthUnit.CENTIMETERS).toString());

        Quantity w1= new Quantity(27,WeightUnit.KILOGRAM);
        Quantity w2= new Quantity(27000,WeightUnit.GRAM);
        System.out.println("Are Weights equal: "+w1.equals(w2));

        Quantity weight1 = new Quantity(5, WeightUnit.KILOGRAM);
        Quantity converted2= weight1.convertTo(WeightUnit.POUND);
        System.out.println(converted2.toString());

        System.out.println(w2.add(w1, WeightUnit.POUND).toString());

//        System.out.println(w1.add(q2));

    }

}
