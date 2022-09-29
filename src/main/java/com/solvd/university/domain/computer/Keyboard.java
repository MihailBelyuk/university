package com.solvd.university.domain.computer;

import java.math.BigDecimal;

public class Keyboard extends HardwareDecorator {

    private BigDecimal price;

    public Keyboard(IComputer newComputer, BigDecimal price) {
        super(newComputer);
        this.price = price;
    }

    public Keyboard(IComputer newComputer) {
        super(newComputer);
    }

    @Override
    public String getHardwareInformation() {
        return computer.getHardwareInformation() + " keyboard A4Tech ";
    }

    @Override
    public BigDecimal getPrice() {
        return computer.getPrice().add(this.price);
    }
}
