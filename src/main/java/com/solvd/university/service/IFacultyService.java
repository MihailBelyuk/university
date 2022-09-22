package com.solvd.university.service;

import com.solvd.university.domain.university.Faculty;

import java.util.List;

public interface IFacultyService {

    void addFacultyRecord(Faculty faculty, Long deansId, Long universitiesId);

    Faculty findFacultyById(Long id);

    void updateFacultyRecord(Faculty faculty, Long deansId, Long universitiesId);

    void deleteFaculty(Faculty faculty);

    List<Faculty> showAllFaculties();

}
