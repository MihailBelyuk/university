package com.solvd.university.domain.university;

import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.university.person.Rector;

import java.time.LocalDate;
import java.util.List;

public class University {

    private Long id;
    private LocalDate establishedAt;
    private Address address;
    private List<Faculty> faculties;
    private Rector rector;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEstablishedAt() {
        return establishedAt;
    }

    public void setEstablishedAt(LocalDate establishedAt) {
        this.establishedAt = establishedAt;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Rector getRector() {
        return rector;
    }

    public void setRector(Rector rector) {
        this.rector = rector;
    }
}
