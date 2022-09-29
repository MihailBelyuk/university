package com.solvd.university.domain.vehicle;

import com.solvd.university.domain.exception.VehicleTypeNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VehicleFactory {

    private static final Logger LOGGER = LogManager.getLogger(VehicleFactory.class);

    public static Vehicle getVehicle(VehicleType vehicleType) {
        Vehicle vehicle = null;
        switch (vehicleType) {
            case CAR:
                vehicle = new Car();
                break;
            case BUS:
                vehicle = new Bus();
                break;
            case VAN:
                vehicle = new Van();
                break;
            default:
                LOGGER.error("Provided vehicle type " + vehicleType + "is not supported.");
                throw new VehicleTypeNotSupportedException("Provided vehicle type " + vehicleType + "is not supported.");
        }
        return vehicle;
    }
}
