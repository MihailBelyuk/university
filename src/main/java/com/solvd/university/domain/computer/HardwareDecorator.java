package com.solvd.university.domain.computer;

import java.math.BigDecimal;

public abstract class HardwareDecorator implements IComputer {

    protected IComputer computer;


    public HardwareDecorator(IComputer newComputer) {
        this.computer = newComputer;
    }

    @Override
    public String getHardwareInformation() {
        return computer.getHardwareInformation();
    }

    @Override
    public BigDecimal getPrice() {
        return computer.getPrice();
    }
}
