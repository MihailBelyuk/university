package com.solvd.university.domain.university;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pencil implements IWrite {

    private static final Logger LOGGER = LogManager.getLogger(Pencil.class);

    private String brand;

    @Override
    public void write() {
        LOGGER.info("Writing with pencil.");
    }

    @Override
    public void fixMarks() {
        LOGGER.info("Erasing marks if needed.");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
