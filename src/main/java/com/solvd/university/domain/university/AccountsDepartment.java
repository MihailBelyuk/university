package com.solvd.university.domain.university;

import com.solvd.university.domain.university.person.Accountant;
import com.solvd.university.domain.university.person.HeadAccountant;

import java.util.List;

public class AccountsDepartment {

    private Long id;
    private HeadAccountant headAccountant;
    private List<Accountant> accountants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
