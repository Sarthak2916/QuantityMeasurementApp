package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;
    private double convertedValue;

    public enum LengthUnit{

        FEET(12.0),
        INCHES(1.0),
        YARDS(36),
        CENTIMETERS( 0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor= conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit){
        this.value= value;
        this.unit= unit;
        this.convertedValue= convertToBaseUnit();
    }
    private double convertToBaseUnit(){
        return unit.conversionFactor*value;
    }
    public boolean compare(Length length){
        return Double.compare(this.convertedValue,length.convertedValue)==0 ? true:false;
    }
    @Override
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=Length.class) return false;
        return compare((Length) obj);
    }

    public static void main(String[] args) {

        Length l1= new Length(5,LengthUnit.FEET);
        Length l2= new Length(60,LengthUnit.INCHES);
        System.out.println("Are lengths equal: "+l1.equals(l2));

        Length l3= new Length(27,LengthUnit.FEET);
        Length l4= new Length(9,LengthUnit.YARDS);
        System.out.println("Are lengths equal: "+l3.equals(l4));

        Length l5= new Length(100,LengthUnit.CENTIMETERS);
        Length l6= new Length(39.3701,LengthUnit.INCHES);
        System.out.println("Are lengths equal: "+l5.equals(l6));
    }
}
