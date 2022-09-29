package com.solvd.university.service.impl;

import com.solvd.university.dao.jdbcimpl.TeacherDaoImpl;
import com.solvd.university.domain.exception.EmptyListException;
import com.solvd.university.domain.exception.NotFullInformationProvidedException;
import com.solvd.university.domain.exception.ResourceNotFoundException;
import com.solvd.university.domain.university.person.Teacher;
import com.solvd.university.service.ITeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {

    private static final Logger LOGGER = LogManager.getLogger(TeacherServiceImpl.class);

    private final TeacherDaoImpl teacherDao;

    public TeacherServiceImpl() {
        teacherDao = new TeacherDaoImpl();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find teacher with id " + id));
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = teacherDao.findAll();
        if (teachers.isEmpty()) {
            LOGGER.error("No teachers records retrieved.");
            throw new EmptyListException("No teachers records retrieved.");
        }
        return teachers;
    }

    @Override
    public void create(Teacher teacher, Long addressesId, Long chairsId) {
        if (teacher.getFirstName() == null || teacher.getLastName() == null || teacher.getBirthday() == null
                || teacher.getSalary() == null || teacher.getAcademicStatus() == null || addressesId == null
                || chairsId == null) {
            LOGGER.error("Failed to add new teacher record,because of lack information.");
            throw new NotFullInformationProvidedException("Failed to add new teacher record,because of lack information.");
        }
        teacherDao.create(teacher, addressesId, chairsId);
    }

    @Override
    public void update(Teacher teacher, Long addressesId, Long chairsId) {
        if (teacher.getId() == null) {
            LOGGER.error("Failed to update teacher record,because teacher id is null.");
            throw new NotFullInformationProvidedException("Failed to add new teacher record,because of lack information.");
        }
        getAll().stream()
                .filter(address1 -> address1.getId().equals(teacher.getId()))
                .findFirst()
                .ifPresentOrElse((address1) -> teacherDao.update(teacher, addressesId, chairsId),
                        () -> create(teacher, addressesId, chairsId));
    }

    @Override
    public void delete(Teacher teacher) {
        teacherDao.delete(getAll().stream()
                .filter(teacher1 -> teacher1.getId().equals(teacher.getId()))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No record matching " + teacher + " in the DB.")));
    }
}
