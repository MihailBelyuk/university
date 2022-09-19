package com.solvd.university.university;

import com.solvd.university.university.person.TradeUnionChairman;

public class TradeUnionCommittee {

    private Long id;
    private TradeUnionChairman chairman;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TradeUnionChairman getChairman() {
        return chairman;
    }

    public void setChairman(TradeUnionChairman chairman) {
        this.chairman = chairman;
    }
}
