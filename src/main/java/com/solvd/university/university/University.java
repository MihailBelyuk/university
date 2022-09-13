package com.solvd.university.university;

import com.solvd.university.address.Address;
import com.solvd.university.university.person.Rector;

import java.time.LocalDate;
import java.util.List;

public class University {

    private Long id;
    private String name;
    private Address address;
    private LocalDate establishedAt;
    private Rector rector;
    private AccountsDepartment accountsDepartment;
    private TradeUnionCommittee tradeUnionCommittee;
    private Library library;
    private List<Faculty> faculties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEstablishedAt() {
        return establishedAt;
    }

    public void setEstablishedAt(LocalDate establishedAt) {
        this.establishedAt = establishedAt;
    }

    public Rector getRector() {
        return rector;
    }

    public void setRector(Rector rector) {
        this.rector = rector;
    }

    public AccountsDepartment getAccountsDepartment() {
        return accountsDepartment;
    }

    public void setAccountsDepartment(AccountsDepartment accountsDepartment) {
        this.accountsDepartment = accountsDepartment;
    }

    public TradeUnionCommittee getTradeUnionCommittee() {
        return tradeUnionCommittee;
    }

    public void setTradeUnionCommittee(TradeUnionCommittee tradeUnionCommittee) {
        this.tradeUnionCommittee = tradeUnionCommittee;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
