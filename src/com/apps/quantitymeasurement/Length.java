package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    public enum LengthUnit{

        FEET(12.0),
        INCHES(1.0);

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
        this.value= convertToBaseUnit();
    }
    private double convertToBaseUnit(){
        return unit.conversionFactor*value;
    }
    public boolean compare(Length length){
        return Double.compare(this.value,length.value)==0 ? true:false;
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
    }
}
