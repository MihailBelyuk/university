package com.solvd.university.domain.computer;

import java.math.BigDecimal;

public class Mouse extends HardwareDecorator {

    private BigDecimal price;

    public Mouse(IComputer newComputer, BigDecimal price) {
        super(newComputer);
        this.price = price;
    }

    public Mouse(IComputer newComputer) {
        super(newComputer);
    }


    @Override
    public String getHardwareInformation() {
        return computer.getHardwareInformation() + " mouse Logitech 1600dpi. ";
    }

    @Override
    public BigDecimal getPrice() {
        return computer.getPrice().add(this.price);
    }
}
