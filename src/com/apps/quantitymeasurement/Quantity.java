package com.apps.quantitymeasurement;

public class Quantity <U extends IMeasurable>{

    private double value;
    private U unit;
    private double convertedValue;

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
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity, U targetUnit){
        return addAndConvert(quantity, targetUnit);
    }
    private <U extends IMeasurable> Quantity<U> addAndConvert(Quantity<U> quantity, U targetUnit){
        double addition= this.convertedValue+quantity.convertedValue;
        double value= convertFromBaseToTargetUnit(addition, targetUnit);
        return new Quantity(value, targetUnit);
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

    }

}
