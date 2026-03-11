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

    public static void main(String[] args) {

        Feet f1= new Feet(5.4);
        Feet f2= new Feet(5.4);

        System.out.println(f1.equals(f2));
    }
}