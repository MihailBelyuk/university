package com.solvd.university.dao.mybatisimpl;

import com.solvd.university.dao.IChairDao;
import com.solvd.university.dao.MyBatisConfig;
import com.solvd.university.domain.university.Chair;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ChairDaoImpl implements IChairDao {

    @Override
    public void create(Chair chair) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IChairDao chairDao = sqlSession.getMapper(IChairDao.class);
            chairDao.create(chair);
        }
    }

    @Override
    public Optional<Chair> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IChairDao chairDao = session.getMapper(IChairDao.class);
            return chairDao.findById(id);
        }
    }


    @Override
    public void update(Chair chair) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IChairDao chairDao = session.getMapper(IChairDao.class);
            chairDao.update(chair);
        }
    }

    @Override
    public void delete(Chair chair) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IChairDao chairDao = session.getMapper(IChairDao.class);
            chairDao.delete(chair);
        }
    }

    @Override
    public List<Chair> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IChairDao chairDao = session.getMapper(IChairDao.class);
            return chairDao.findAll();
        }
    }
}
