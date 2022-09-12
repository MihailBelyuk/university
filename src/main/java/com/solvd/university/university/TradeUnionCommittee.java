package com.solvd.university.university;

import com.solvd.university.university.person.TradeUnionChairman;

public class TradeUnionCommittee {

    private long id;
    private TradeUnionChairman chairman;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TradeUnionChairman getChairman() {
        return chairman;
    }

    public void setChairman(TradeUnionChairman chairman) {
        this.chairman = chairman;
    }
}
