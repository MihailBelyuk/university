package com.solvd.university.service;

import com.solvd.university.domain.university.person.Teacher;

import java.util.List;

public interface ITeacherService {

    Teacher findTeacher(Long id);

    List<Teacher> findAllTeachers();

    void addTeacher(Teacher teacher, Long addressesId, Long chairsId);

    void updateTeacherInfo(Teacher teacher, Long addressesId, Long chairsId);

    void deleteTeacher(Teacher teacher);

}
