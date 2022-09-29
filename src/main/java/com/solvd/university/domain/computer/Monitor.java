package com.solvd.university.domain.computer;

import java.math.BigDecimal;

public class Monitor extends HardwareDecorator {

    private BigDecimal price;

    public Monitor(IComputer newComputer, BigDecimal price) {
        super(newComputer);
        this.price = price;
    }

    public Monitor(IComputer newComputer) {
        super(newComputer);
    }


    @Override
    public String getHardwareInformation() {
        return computer.getHardwareInformation() + " Monitor LG 21\" ";
    }

    @Override
    public BigDecimal getPrice() {
        return computer.getPrice().add(this.price);
    }
}
