package com.solvd.university.dao;

import com.solvd.university.domain.university.person.Dean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface IDeanDao {

    void create(@Param("dean") Dean dean, @Param("addressId") Long addressId);

    Optional<Dean> findById(Long id);

    void update(@Param("dean") Dean dean, @Param("addressId") Long addressId);

    void delete(Dean dean);

    List<Dean> findAll();

}
