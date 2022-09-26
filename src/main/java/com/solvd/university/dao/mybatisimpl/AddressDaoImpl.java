package com.solvd.university.dao.mybatisimpl;

import com.solvd.university.dao.IAddressDao;
import com.solvd.university.dao.MyBatisConfig;
import com.solvd.university.domain.address.Address;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements IAddressDao {

    @Override
    public void create(Address address) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IAddressDao addressDao = session.getMapper(IAddressDao.class);
            addressDao.create(address);
        }
    }

    @Override
    public Optional<Address> findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IAddressDao addressDao = session.getMapper(IAddressDao.class);
            return addressDao.findById(id);
        }
    }

    @Override
    public void update(Address address) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IAddressDao addressDao = session.getMapper(IAddressDao.class);
            addressDao.update(address);
        }
    }

    @Override
    public void delete(Address address) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IAddressDao addressDao = session.getMapper(IAddressDao.class);
            addressDao.delete(address);
        }
    }

    @Override
    public List<Address> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            IAddressDao addressDao = session.getMapper(IAddressDao.class);
            return addressDao.findAll();
        }
    }
}
