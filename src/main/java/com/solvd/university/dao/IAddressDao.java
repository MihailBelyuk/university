package com.solvd.university.dao;


import com.solvd.university.domain.address.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressDao {

    void create(Address address);

    Optional<Address> findById(Long id);

    void update(Address address);

    void delete(Address address);

    List<Address> findAll();

}
