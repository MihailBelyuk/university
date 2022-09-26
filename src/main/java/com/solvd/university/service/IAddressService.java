package com.solvd.university.service;

import com.solvd.university.domain.address.Address;

import java.util.List;

public interface IAddressService {

    void create(Address address);

    Address getById(Long id);

    void update(Address address);

    void delete(Address address);

    List<Address> getAll();

}
