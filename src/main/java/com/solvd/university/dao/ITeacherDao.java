package com.solvd.university.dao;


import com.solvd.university.domain.university.person.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface ITeacherDao {

    void create(@Param("teacher") Teacher teacher, @Param("addressId") Long addressId, @Param("chairId") Long chairsId);

    Optional<Teacher> findById(Long id);

    void update(@Param("teacher") Teacher teacher, @Param("addressId") Long addressId, @Param("chairId") Long chairId);

    void delete(Teacher teacher);

    List<Teacher> findAll();

}
