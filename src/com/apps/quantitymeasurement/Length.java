package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;
    private double convertedValue;

    public Length(double value, LengthUnit unit){
        this.value= value;
        this.unit= unit;
        this.convertedValue= convertToBaseUnit();
    }
    private double convertToBaseUnit(){
        return unit.convertToBaseUnit(value);
    }
    public boolean compare(Length length){
        return Double.compare(this.convertedValue, length.convertedValue)==0 ? true:false;
    }
    @Override
    public boolean equals(Object obj) throws IllegalArgumentException{
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=Length.class) throw new IllegalArgumentException("Not a valid Argument");
        return compare((Length) obj);
    }

    public Length convertTo(LengthUnit targetUnit) throws IllegalArgumentException{
        if(targetUnit==null) throw new IllegalArgumentException("Not a valid Argument");
        double val= convertFromBaseToTargetUnit(convertedValue, targetUnit);
        return new Length(val, targetUnit);
    }

    public Length add(Length length){
        return addAndConvert(length, this.unit);
    }
    public Length add(Length length, LengthUnit targetUnit){
        return addAndConvert(length, targetUnit);
    }
    private Length addAndConvert(Length length, LengthUnit targetUnit){
        double addition= this.convertedValue+length.convertedValue;
        double value= convertFromBaseToTargetUnit(addition, targetUnit);
        return new Length(value, targetUnit);
    }
    private double convertFromBaseToTargetUnit(double LengthInInches, LengthUnit targetUnit){
        return unit.convertFromBaseToTargetUnit(LengthInInches, targetUnit);
    }

    public String toString(){
        return value+" "+unit;
    }

    public static void main(String[] args) {

        Length l1= new Length(27,LengthUnit.FEET);
        Length l2= new Length(9,LengthUnit.YARDS);
        System.out.println("Are lengths equal: "+l1.equals(l2));

        Length len1= new Length(5, LengthUnit.FEET);
        Length converted= len1.convertTo(LengthUnit.INCHES);
        System.out.println(converted.toString());

        System.out.println(l2.add(l1, LengthUnit.CENTIMETERS).toString());

    }
}
