package com.solvd.university.dao;


import com.solvd.university.domain.university.Chair;

import java.util.List;
import java.util.Optional;

public interface IChairDao {

    void create(Chair chair);

    Optional<Chair> findById(Long id);

    void update(Chair chair);

    void delete(Chair chair);

    List<Chair> findAll();

}
