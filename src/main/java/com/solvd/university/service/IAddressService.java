package com.solvd.university.service;

import com.solvd.university.domain.address.Address;

import java.util.List;

public interface IAddressService {

    void addAddress(Address address);

    void deleteAddress(Address address);

    void updateAddress(Address address);

    Address findAddressById(Long id);

    List<Address> showAllAddresses();

}
