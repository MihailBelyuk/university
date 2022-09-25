package com.solvd.university.dao.mybatisimpl;

import com.solvd.university.dao.ITeacherDao;
import com.solvd.university.dao.MyBatisConfig;
import com.solvd.university.domain.university.person.Teacher;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class TeacherDaoImpl implements ITeacherDao {

    @Override
    public void create(Teacher teacher, Long addressesId, Long chairsId) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        ITeacherDao teacherDao = session.getMapper(ITeacherDao.class);
        teacherDao.create(teacher, addressesId, chairsId);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        ITeacherDao teacherDao = session.getMapper(ITeacherDao.class);
        return teacherDao.findById(id);
    }

    @Override
    public void update(Teacher teacher, Long addressesId, Long chairsId) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        ITeacherDao teacherDao = session.getMapper(ITeacherDao.class);
        teacherDao.update(teacher, addressesId, chairsId);
    }

    @Override
    public void delete(Teacher teacher) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        ITeacherDao teacherDao = session.getMapper(ITeacherDao.class);
        teacherDao.delete(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true);
        ITeacherDao teacherDao = session.getMapper(ITeacherDao.class);
        return teacherDao.findAll();
    }

}
