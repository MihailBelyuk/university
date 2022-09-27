package com.solvd.university.service;

import com.solvd.university.domain.university.Chair;

import java.util.List;

public interface IChairService {

    void create(Chair chair);

    Chair getById(Long id);

    void update(Chair chair);

    void delete(Chair chair);

    List<Chair> getAll();

}
