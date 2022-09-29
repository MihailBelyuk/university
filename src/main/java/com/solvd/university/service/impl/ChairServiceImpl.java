package com.solvd.university.service.impl;

//import com.solvd.university.dao.jdbcimpl.ChairDaoImpl;

import com.solvd.university.dao.mybatisimpl.ChairDaoImpl;
import com.solvd.university.domain.exception.EmptyListException;
import com.solvd.university.domain.exception.NotFullInformationProvidedException;
import com.solvd.university.domain.exception.ResourceNotFoundException;
import com.solvd.university.domain.university.Chair;
import com.solvd.university.service.IChairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChairServiceImpl implements IChairService {

    private static final Logger LOGGER = LogManager.getLogger(ChairServiceImpl.class);

    private final ChairDaoImpl chairDao;

    public ChairServiceImpl() {
        chairDao = new ChairDaoImpl();
    }

    @Override
    public Chair getById(Long id) {
        return chairDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to find chair with id " + id));
    }

    @Override
    public void create(Chair chair) {
        if (chair.getName() == null) {
            LOGGER.error("Failed to add new chair, because of lack information.");
            throw new NotFullInformationProvidedException("Failed to add new chair, because of lack information.");
        }
        chairDao.create(chair);
    }

    @Override
    public void update(Chair chair) {
        if (chair.getId() == null || chair.getName() == null) {
            LOGGER.error("Failed to update chair, because of lack information.");
            throw new NotFullInformationProvidedException("Failed to update chair, because of lack information.");
        }
        getAll().stream()
                .filter(chair1 -> chair1.getId().equals(chair.getId()))
                .findFirst()
                .ifPresentOrElse((address1) -> chairDao.update(chair), () -> create(chair));
    }

    @Override
    public void delete(Chair chair) {
        chairDao.delete(getAll().stream()
                .filter(chair1 -> chair1.getId().equals(chair.getId()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No record matching " + chair + " in the DB.")));
    }

    @Override
    public List<Chair> getAll() {
        List<Chair> chairs = chairDao.findAll();
        if (chairs.isEmpty()) {
            LOGGER.error("No chairs records retrieved");
            throw new EmptyListException("No chairs records retrieved");
        }
        return chairs;
    }


}
