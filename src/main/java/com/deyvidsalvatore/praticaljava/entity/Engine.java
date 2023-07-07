package com.deyvidsalvatore.praticaljava.entity;

public class Engine {

    private String fuelType;

    private int horsePower;

    public Engine() {

    }

    public Engine(String fuelType, int horsePower) {
        this.fuelType = fuelType;
        this.horsePower = horsePower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "fuelType='" + fuelType + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
