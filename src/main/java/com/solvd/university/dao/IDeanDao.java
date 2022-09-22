package com.solvd.university.dao;

import com.solvd.university.domain.university.person.Dean;

import java.util.List;
import java.util.Optional;

public interface IDeanDao {

    void create(Dean dean, Long addressesId);

    Optional<Dean> findById(Long id);

    void update(Dean dean, Long addressesId);

    void delete(Dean dean);

    List<Dean> findAll();

}
