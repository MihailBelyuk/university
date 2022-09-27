package com.solvd.university.service;

import com.solvd.university.domain.university.Faculty;

import java.util.List;

public interface IFacultyService {

    void create(Faculty faculty, Long deansId, Long universitiesId);

    Faculty getById(Long id);

    void update(Faculty faculty, Long deansId, Long universitiesId);

    void delete(Faculty faculty);

    List<Faculty> getAll();

}
