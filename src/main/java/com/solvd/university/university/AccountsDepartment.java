package com.solvd.university.university;

import com.solvd.university.university.person.Accountant;
import com.solvd.university.university.person.HeadAccountant;

import java.util.List;

public class AccountsDepartment {

    private long id;
    private HeadAccountant headAccountant;
    private List<Accountant> accountants;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HeadAccountant getHeadAccountant() {
        return headAccountant;
    }

    public void setHeadAccountant(HeadAccountant headAccountant) {
        this.headAccountant = headAccountant;
    }

    public List<Accountant> getAccountants() {
        return accountants;
    }

    public void setAccountants(List<Accountant> accountants) {
        this.accountants = accountants;
    }
}
