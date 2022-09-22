package com.solvd.university.service.impl;

import com.solvd.university.dao.impl.DeanDaoImpl;
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

    DeanServiceImpl() {
        deanDao = new DeanDaoImpl();
    }

    @Override
    public void addDeanRecord(Dean dean, Long addressesId) {
        if (dean.getFirstName() == null || dean.getLastName() == null || dean.getBirthday() == null
                || dean.getSalary() == null || addressesId == null) {
            LOGGER.error("Failed to add new dean record,because of lack information.");
            throw new NotFullInformationProvidedException("Failed to add new dean record,because of lack information.");
        }
        deanDao.create(dean, addressesId);
    }

    @Override
    public void updateDeanRecord(Dean dean, Long addressesId) {
        if (dean.getFirstName() == null || dean.getLastName() == null || dean.getBirthday() == null
                || dean.getSalary() == null || addressesId == null) {
            LOGGER.error("Failed to update dean record,because of lack information.");
            throw new NotFullInformationProvidedException("Failed to update dean record,because of lack information.");
        }
        showAllDeans().stream()
                .filter(dean1 -> dean1.getId().equals(dean.getId()))
                .findFirst()
                .ifPresentOrElse(dean1 -> deanDao.update(dean, addressesId), () -> addDeanRecord(dean, addressesId));
    }

    @Override
    public void deleteDeanRecord(Dean dean) {
        deanDao.delete(showAllDeans().stream()
                .filter(dean1 -> dean1.getId().equals(dean.getId()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No record matching " + dean + " in the DB.")));
    }

    @Override
    public Dean findDeanById(Long id) {
        return deanDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to find dean with id " + id));
    }

    @Override
    public List<Dean> showAllDeans() {
        List<Dean> deans = deanDao.findAll();
        if (deans.isEmpty()) {
            LOGGER.error("No deans records retrieved.");
            throw new EmptyListException("No deans records retrieved.");
        }
        return deans;
    }
}
