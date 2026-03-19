package com.apps.quantitymeasurement;

public class Weight {

    private double value;
    private WeightUnit unit;
    private double convertedValue;

    public Weight(double value, WeightUnit unit) throws IllegalArgumentException{
        this.value= value;
        if(unit==null) throw new IllegalArgumentException("Not a valid unit");
        this.unit= unit;
        this.convertedValue= convertToBaseUnit();
    }
    private double convertToBaseUnit(){
        return unit.convertToBaseUnit(value);
    }
    public boolean compare(Weight Weight){
        return Math.abs(this.convertedValue-Weight.convertedValue)<0.01;
    }
    @Override
    public boolean equals(Object obj) throws IllegalArgumentException{
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=Weight.class) throw new IllegalArgumentException("Not a valid Argument");
        return compare((Weight) obj);
    }

    public Weight convertTo(WeightUnit targetUnit) throws IllegalArgumentException{
        if(targetUnit==null) throw new IllegalArgumentException("Not a valid Argument");
        double val= convertFromBaseToTargetUnit(convertedValue, targetUnit);
        return new Weight(val, targetUnit);
    }

    public Weight add(Weight Weight){
        return addAndConvert(Weight, this.unit);
    }
    public Weight add(Weight Weight, WeightUnit targetUnit){
        return addAndConvert(Weight, targetUnit);
    }
    private Weight addAndConvert(Weight Weight, WeightUnit targetUnit){
        double addition= this.convertedValue+Weight.convertedValue;
        double value= convertFromBaseToTargetUnit(addition, targetUnit);
        return new Weight(value, targetUnit);
    }
    private double convertFromBaseToTargetUnit(double WeightInGrams, WeightUnit targetUnit){
        return unit.convertFromBaseToTargetUnit(WeightInGrams, targetUnit);
    }

    public String toString(){
        return value+" "+unit;
    }

    public static void main(String[] args) {

        Weight w1= new Weight(27,WeightUnit.KILOGRAM);
        Weight w2= new Weight(27000,WeightUnit.GRAM);
        System.out.println("Are Weights equal: "+w1.equals(w2));

        Weight weight1 = new Weight(5, WeightUnit.KILOGRAM);
        Weight converted= weight1.convertTo(WeightUnit.POUND);
        System.out.println(converted.toString());

        System.out.println(w2.add(w1, WeightUnit.POUND).toString());

    }
}
