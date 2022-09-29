package com.solvd.university.domain.computer;

import java.math.BigDecimal;

public class Processor extends HardwareDecorator {

    private BigDecimal price;

    public Processor(IComputer newComputer, BigDecimal price) {
        super(newComputer);
        this.price = price;
    }

    public Processor(IComputer newComputer) {
        super(newComputer);
    }


    @Override
    public String getHardwareInformation() {
        return computer.getHardwareInformation() + "Processor Intel core i9 ";
    }

    @Override
    public BigDecimal getPrice() {
        return computer.getPrice().add(this.price);
    }
}
