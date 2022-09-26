package com.solvd.university.service.impl;

import com.solvd.university.dao.jdbcimpl.FacultyDaoImpl;
//import com.solvd.university.dao.mybatisimpl.FacultyDaoImpl;
import com.solvd.university.domain.exception.EmptyListException;
import com.solvd.university.domain.exception.NotFullInformationProvidedException;
import com.solvd.university.domain.exception.ResourceNotFoundException;
import com.solvd.university.domain.university.Faculty;
import com.solvd.university.service.IFacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FacultyServiceImpl implements IFacultyService {

    private static final Logger LOGGER = LogManager.getLogger(FacultyServiceImpl.class);

    private final FacultyDaoImpl facultyDao;

    public FacultyServiceImpl() {
        facultyDao = new FacultyDaoImpl();
    }

    @Override
    public void create(Faculty faculty, Long deansId, Long universitiesId) {
        if (faculty.getName() == null || deansId == null || universitiesId == null) {
            LOGGER.error("Failed to add new faculty, because of lack information.");
            throw new NotFullInformationProvidedException("Failed to add new faculty, because of lack information.");
        }
        facultyDao.create(faculty, deansId, universitiesId);
    }

    @Override
    public Faculty getById(Long id) {
        return facultyDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to find faculty with id " + id));
    }

    @Override
    public void update(Faculty faculty, Long deansId, Long universitiesId) {
        if (faculty.getId() == null || faculty.getName() == null || deansId == null || universitiesId == null) {
            LOGGER.error("Failed to update faculty, because of lack information.");
            throw new NotFullInformationProvidedException("Failed to update faculty, because of lack information.");
        }
        getAll().stream()
                .filter(faculty1 -> faculty1.getId().equals(faculty.getId()))
                .findFirst()
                .ifPresentOrElse(dean1 -> facultyDao.update(faculty, deansId, universitiesId),
                        () -> create(faculty, deansId, universitiesId));
    }

    @Override
    public void delete(Faculty faculty) {
        facultyDao.delete(getAll().stream()
                .filter(faculty1 -> faculty1.getId().equals(faculty.getId()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No record matching " + faculty + " in the DB.")));
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faculties = facultyDao.findAll();
        if (faculties.isEmpty()) {
            LOGGER.error("No faculties records retrieved from the DB.");
            throw new EmptyListException("No faculties records retrieved from the DB.");
        }
        return faculties;
    }
}
