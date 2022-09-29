package com.solvd.university.domain.computer;

import java.math.BigDecimal;

public class VideoCard extends HardwareDecorator {

    private BigDecimal price;

    public VideoCard(IComputer newComputer, BigDecimal price) {
        super(newComputer);
        this.price = price;
    }

    public VideoCard(IComputer newComputer) {
        super(newComputer);
    }

    @Override
    public String getHardwareInformation() {
        return computer.getHardwareInformation() + " Video card GeForce GTX-3060 ";
    }

    @Override
    public BigDecimal getPrice() {
        return computer.getPrice().add(this.price);
    }
}
