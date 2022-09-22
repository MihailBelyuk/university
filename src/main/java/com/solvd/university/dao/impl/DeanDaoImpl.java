package com.solvd.university.dao.impl;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.IDeanDao;
import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.exception.CreateFailedException;
import com.solvd.university.domain.exception.DeleteFailedException;
import com.solvd.university.domain.exception.RetrieveInformationFailedException;
import com.solvd.university.domain.exception.UpdateFailedException;
import com.solvd.university.domain.university.person.Dean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeanDaoImpl implements IDeanDao {

    private static final Logger LOGGER = LogManager.getLogger(DeanDaoImpl.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_DEAN = "create into deans(first_name, last_name,birthday, " +
            "salary, addresses_id values (?,?,?,?,?)";
    private static final String FIND_BY_ID = "select id as dean_id, first_name as dean_name, " +
            "last_name as dean_last_name, birthday as dean_birthday, salary as dean_salary where id=?";
    private static final String UPDATE = "update deans set first_name = ?, last_name = ?, birthday=?, salary = ?," +
            "addresses_id=? where id=?";
    private static final String DELETE = "delete from deans where id=?";
    private static final String FIND_ALL = "select d.id as dean_id, d.first_name as dean_first_name, " +
            "d.last_name as dean_last_name, d.birthday as dean_birthday, d.salary as dean_salary, " +
            "a.id as address_id from deans d left join addresses a on " +
            "d.addresses_id =a.id";
    private static final String ADDRESS_ID = "address_id";

    private static final String DEAN_ID = "dean_id";
    private static final String DEAN_FIRST_NAME = "dean_first_name";
    private static final String DEAN_LAST_NAME = "dean_last_name";
    private static final String DEAN_BIRTHDAY = "dean_birthday";
    private static final String DEAN_SALARY = "dean_salary";


    @Override
    public void create(Dean dean, Long addressesId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DEAN, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, dean.getFirstName());
            preparedStatement.setString(2, dean.getLastName());
            preparedStatement.setDate(3, Date.valueOf(dean.getBirthday()));
            preparedStatement.setBigDecimal(4, dean.getSalary());
            preparedStatement.setLong(5, addressesId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to create new dean record into the DB.", e);
            throw new CreateFailedException("Failed to create new dean record into the DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Dean> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Dean dean = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = AddressDaoImpl.mapAddress(resultSet);
                dean = deanMapper(resultSet);
                dean.setAddress(address);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to fin dean with id " + id, e);
            throw new RetrieveInformationFailedException("Failed to fin dean with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(dean);
    }

    @Override
    public void update(Dean dean, Long addressesId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, dean.getFirstName());
            preparedStatement.setString(2, dean.getLastName());
            preparedStatement.setDate(3, Date.valueOf(dean.getBirthday()));
            preparedStatement.setBigDecimal(4, dean.getSalary());
            preparedStatement.setLong(5, addressesId);
            preparedStatement.setLong(6, dean.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update dean information in the DB.", e);
            throw new UpdateFailedException("Failed to update dean information in the DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Dean dean) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, dean.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to delete dean information from DB.", e);
            throw new DeleteFailedException("Failed to delete dean information from DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Dean> findAll() {
        List<Dean> deans = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getLong(ADDRESS_ID));
                Dean dean = deanMapper(resultSet);
                dean.setAddress(address);
                deans.add(dean);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve all deans information from DB.", e);
            throw new RetrieveInformationFailedException("Failed to retrieve all deans information from DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return deans;
    }

    public static Dean deanMapper(ResultSet resultSet) throws SQLException {
        Dean dean = new Dean();
        dean.setId(resultSet.getLong(DEAN_ID));
        dean.setFirstName((resultSet.getString(DEAN_FIRST_NAME)));
        dean.setLastName(resultSet.getString(DEAN_LAST_NAME));
        dean.setBirthday(LocalDate.parse(resultSet.getString(DEAN_BIRTHDAY)));
        dean.setSalary(resultSet.getBigDecimal(DEAN_SALARY));
        return dean;
    }
}
