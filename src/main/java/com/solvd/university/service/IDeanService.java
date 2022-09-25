package com.solvd.university.service;

import com.solvd.university.domain.university.person.Dean;

import java.util.List;

public interface IDeanService {

    void create(Dean dean, Long addressesId);

    Dean getById(Long id);

    void update(Dean dean, Long addressesId);

    void delete(Dean dean);

    List<Dean> getAll();

}

