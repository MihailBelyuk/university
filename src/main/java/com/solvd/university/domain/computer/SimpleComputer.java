package com.solvd.university.domain.computer;

import java.math.BigDecimal;

public class SimpleComputer implements IComputer {

    private BigDecimal price;

    @Override
    public String getHardwareInformation() {
        return "Desktop ";
    }

    @Override
    public BigDecimal getPrice() {
        if (price == null) {
            price = new BigDecimal(0);
        }
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
