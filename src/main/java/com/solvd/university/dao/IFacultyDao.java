package com.solvd.university.dao;

import com.solvd.university.domain.university.Faculty;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface IFacultyDao {

    void create(Faculty faculty, Long deansId, Long universitiesId);

    Optional<Faculty> findById(Long id);

    void update(@Param("faculty") Faculty faculty, @Param("deanId") Long deanId, @Param("universityId") Long universityId);

    void delete(Faculty faculty);

    List<Faculty> findAll();

}
