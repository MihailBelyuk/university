package com.solvd.university.dao.mybatisimpl;

import com.solvd.university.dao.IFacultyDao;
import com.solvd.university.dao.MyBatisConfig;
import com.solvd.university.domain.university.Faculty;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class FacultyDaoImpl implements IFacultyDao {

    @Override
    public void create(Faculty faculty, Long deansId, Long universitiesId) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        IFacultyDao facultyDao = session.getMapper(IFacultyDao.class);
        facultyDao.create(faculty, deansId, universitiesId);
    }

    @Override
    public void update(Faculty faculty, Long deansId, Long universitiesId) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        IFacultyDao facultyDao = session.getMapper(IFacultyDao.class);
        facultyDao.update(faculty, deansId, universitiesId);
    }

    @Override
    public void delete(Faculty faculty) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        IFacultyDao facultyDao = session.getMapper(IFacultyDao.class);
        facultyDao.delete(faculty);
    }

    @Override
    public Optional<Faculty> findById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        IFacultyDao facultyDao = session.getMapper(IFacultyDao.class);
        return facultyDao.findById(id);
    }

    @Override
    public List<Faculty> findAll() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        IFacultyDao facultyDao = session.getMapper(IFacultyDao.class);
        return facultyDao.findAll();
    }
}
