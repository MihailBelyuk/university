package com.solvd.university.service;

import com.solvd.university.domain.university.Chair;

import java.util.List;

public interface IChairService {

    Chair findChairById(Long id);

    void addChairRecord(Chair chair);

    void updateChair(Chair chair);

    void deleteChair(Chair chair);

    List<Chair> showAllChairs();

}
