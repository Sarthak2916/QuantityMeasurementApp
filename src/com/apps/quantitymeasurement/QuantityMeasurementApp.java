package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Feet{

        private final double value;

        public Feet(double value){
            this.value=value;
        }
        @Override
        public boolean equals(Object obj){
            if(obj==null) return false;
            if(obj.getClass() != Feet.class) return false;
            return Double.compare(this.value,((Feet) obj).value)==0 ? true : false;
        }
    }
    public static class Inches{

        private final double value;

        public Inches(double value){
            this.value=value;
        }
        @Override
        public boolean equals(Object obj){
            if(obj==null) return false;
            if(obj.getClass() != Inches.class) return false;
            return Double.compare(this.value,((Inches) obj).value)==0 ? true : false;
        }
    }
    public static void demonstrateFeetEquality(){
        Feet f1= new Feet(5.4);
        Feet f2= new Feet(5.4);
        System.out.println(f1.equals(f2));
    }
    public static void demonstrateInchesEquality(){
        Inches i1= new Inches(3);
        Inches i2= new Inches(3);
        System.out.println(i1.equals(i2));
    }
    public static void main(String[] args) {

        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}