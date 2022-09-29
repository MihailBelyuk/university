package com.solvd.university.service.impl;

//import com.solvd.university.dao.jdbcimpl.AddressDaoImpl;

import com.solvd.university.dao.mybatisimpl.AddressDaoImpl;
import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.exception.EmptyListException;
import com.solvd.university.domain.exception.NotFullInformationProvidedException;
import com.solvd.university.domain.exception.ResourceNotFoundException;
import com.solvd.university.service.IAddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddressServiceImpl implements IAddressService {

    private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);

    private final AddressDaoImpl addressDao;

    public AddressServiceImpl() {
        this.addressDao = new AddressDaoImpl();
    }

    @Override
    public void create(Address address) {
        if (address.getCity() == null
                || address.getStreet() == null
                || address.getHouse() == null
                || address.getIndex() == null) {
            LOGGER.error("Provided address information is incomplete.");
            throw new NotFullInformationProvidedException("Provided address information is incomplete.");
        } else {
            addressDao.create(address);
        }
    }

    @Override
    public void delete(Address address) {
        addressDao.delete(getAll().stream()
                .filter(address1 -> address1.getId().equals(address.getId()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No record matching " + address + " in the DB.")));
    }

    @Override
    public void update(Address address) {
        if (address.getId() == null || address.getCity() == null || address.getStreet() == null
                || address.getHouse() == null || address.getIndex() == null) {
            LOGGER.error("Provided address information is incomplete");
            throw new NotFullInformationProvidedException("Provided address information is incomplete");
        }
        getAll().stream()
                .filter(address1 -> address1.getId().equals(address.getId()))
                .findFirst()
                .ifPresentOrElse((address1) -> addressDao.update(address), () -> create(address));
    }

    @Override
    public Address getById(Long id) {
        return addressDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address with id " + id + " has not been found."));
    }

    @Override
    public List<Address> getAll() {
        List<Address> addresses = addressDao.findAll();
        if (addresses.isEmpty()) {
            LOGGER.error("No address records retrieved.");
            throw new EmptyListException("No addresses records retrieved.");
        }
        return addressDao.findAll();
    }
}
