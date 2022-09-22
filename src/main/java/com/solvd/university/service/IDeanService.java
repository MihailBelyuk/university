package com.solvd.university.service;

import com.solvd.university.domain.university.person.Dean;

import java.util.List;

public interface IDeanService {

    void addDeanRecord(Dean dean, Long addressesId);

    void updateDeanRecord(Dean dean, Long addressesId);

    void deleteDeanRecord(Dean dean);

    Dean findDeanById(Long id);

    List<Dean> showAllDeans();

}

