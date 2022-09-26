package com.solvd.university.service.impl;

//import com.solvd.university.dao.jdbcimpl.DeanDaoImpl;
import com.solvd.university.dao.mybatisimpl.DeanDaoImpl;
import com.solvd.university.domain.exception.EmptyListException;
import com.solvd.university.domain.exception.NotFullInformationProvidedException;
import com.solvd.university.domain.exception.ResourceNotFoundException;
import com.solvd.university.domain.university.person.Dean;
import com.solvd.university.service.IDeanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DeanServiceImpl implements IDeanService {

    private static final Logger LOGGER = LogManager.getLogger(DeanServiceImpl.class);

    private final DeanDaoImpl deanDao;

    public DeanServiceImpl() {
        deanDao = new DeanDaoImpl();
    }

    @Override
    public void create(Dean dean, Long addressesId) {
        if (dean.getFirstName() == null || dean.getLastName() == null || dean.getBirthday() == null
                || dean.getSalary() == null || addressesId == null) {
            LOGGER.error("Failed to add new dean record,because of lack information.");
            throw new NotFullInformationProvidedException("Failed to add new dean record,because of lack information.");
        }
        deanDao.create(dean, addressesId);
    }

    @Override
    public void update(Dean dean, Long addressesId) {
        if (dean.getFirstName() == null || dean.getLastName() == null || dean.getBirthday() == null
                || dean.getSalary() == null || addressesId == null) {
            LOGGER.error("Failed to update dean record,because of lack information.");
            throw new NotFullInformationProvidedException("Failed to update dean record,because of lack information.");
        }
        getAll().stream()
                .filter(dean1 -> dean1.getId().equals(dean.getId()))
                .findFirst()
                .ifPresentOrElse(dean1 -> deanDao.update(dean, addressesId), () -> create(dean, addressesId));
    }

    @Override
    public void delete(Dean dean) {
        deanDao.delete(getAll().stream()
                .filter(dean1 -> dean1.getId().equals(dean.getId()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No record matching " + dean + " in the DB.")));
    }

    @Override
    public Dean getById(Long id) {
        return deanDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to find dean with id " + id));
    }

    @Override
    public List<Dean> getAll() {
        List<Dean> deans = deanDao.findAll();
        if (deans.isEmpty()) {
            LOGGER.error("No deans records retrieved.");
            throw new EmptyListException("No deans records retrieved.");
        }
        return deans;
    }
}
