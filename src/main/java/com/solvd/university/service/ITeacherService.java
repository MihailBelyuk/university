package com.solvd.university.service;

import com.solvd.university.domain.university.person.Teacher;

import java.util.List;

public interface ITeacherService {

    void create(Teacher teacher, Long addressesId, Long chairsId);

    Teacher getById(Long id);

    void update(Teacher teacher, Long addressesId, Long chairsId);

    void delete(Teacher teacher);

    List<Teacher> getAll();

}
