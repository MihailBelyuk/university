package com.solvd.university.domain.university;

import com.solvd.university.domain.vehicle.Vehicle;

import java.util.List;

public class AutoPark {

    private List<Vehicle> vehicleList;

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
