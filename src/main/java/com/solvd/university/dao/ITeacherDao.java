package com.solvd.university.dao;


import com.solvd.university.domain.university.person.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherDao {

    void create(Teacher teacher, Long addressesId, Long chairsId);

    Optional<Teacher> findById(Long id);

    void update(Teacher teacher, Long addressesId, Long chairsId);

    void delete(Teacher teacher);

    List<Teacher> findAll();

}
