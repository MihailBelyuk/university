package com.solvd.university.dao.mybatisimpl;

import com.solvd.university.dao.IDeanDao;
import com.solvd.university.dao.MyBatisConfig;
import com.solvd.university.domain.university.person.Dean;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class DeanDaoImpl implements IDeanDao {

    @Override
    public void create(Dean dean, Long addressesId) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IDeanDao deanDao = session.getMapper(IDeanDao.class);
            deanDao.create(dean, addressesId);
        }
    }

    @Override
    public Optional<Dean> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IDeanDao deanDao = session.getMapper(IDeanDao.class);
            return deanDao.findById(id);
        }
    }

    @Override
    public void update(Dean dean, Long addressesId) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IDeanDao deanDao = session.getMapper(IDeanDao.class);
            deanDao.update(dean, addressesId);
        }
    }

    @Override
    public void delete(Dean dean) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IDeanDao deanDao = session.getMapper(IDeanDao.class);
            deanDao.delete(dean);
        }
    }

    @Override
    public List<Dean> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IDeanDao deanDao = session.getMapper(IDeanDao.class);
            return deanDao.findAll();
        }
    }
}
