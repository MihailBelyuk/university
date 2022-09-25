package com.solvd.university.dao;

import com.solvd.university.domain.university.Faculty;

import java.util.List;
import java.util.Optional;

public interface IFacultyDao {

    void create(Faculty faculty, Long deansId, Long universitiesId);

    Optional<Faculty> findById(Long id);

    void update(Faculty faculty, Long deansId, Long universitiesId);

    void delete(Faculty faculty);

    List<Faculty> findAll();

}
