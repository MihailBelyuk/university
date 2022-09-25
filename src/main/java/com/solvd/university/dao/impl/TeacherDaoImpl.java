package com.solvd.university.dao.impl;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.ITeacherDao;
import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.exception.CreateFailedException;
import com.solvd.university.domain.exception.DeleteFailedException;
import com.solvd.university.domain.exception.RetrieveInformationFailedException;
import com.solvd.university.domain.exception.UpdateFailedException;
import com.solvd.university.domain.university.person.AcademicStatus;
import com.solvd.university.domain.university.person.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherDaoImpl implements ITeacherDao {

    private static final Logger LOGGER = LogManager.getLogger(TeacherDaoImpl.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String FIND_BY_ID = "select t.id as teacher_id," +
            "t.first_name as teacher_first_name, t.last_name as teacher_last_name, t.salary as teacher_salary, " +
            "t.birthday as teacher_birthday, t.academic_status as teacher_academic_status, a.id as address_id, " +
            "a.city, a.street, a.house, a.flat, a.post_index " +
            "from teachers t left join addresses a on t.addresses_id = a.id where t.id = ?";
    private static final String FIND_ALL = "select t.id as teacher_id, " +
            "t.first_name as teacher_first_name, t.last_name as teacher_last_name,t.salary as teacher_salary, " +
            "t.birthday as teacher_birthday, t.academic_status as teacher_academic_status, a.id as address_id, a.city, a.street," +
            "a.house, a.flat, a.post_index from teachers t left join addresses a on t.addresses_id = a.id";
    private static final String INSERT = "create into teachers(first_name,last_name, birthday, " +
            "salary, academic_status, chairs_id, addresses_id) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "delete from teachers where id = ?";
    private static final String UPDATE_FULL_INFO = "update first_name, last_name, birthday, salary, " +
            "academic_status, addresses_id, chairs_id set (?, ?, ?, ?, ?, ?, ?) where id = ?";
    private static final String FIND_TEACHERS_BY_CHAIR = "select id as teacher_id, first_name as teacher_first_name, " +
            "last_name as teacher_last_name, birthday as teacher_birthday, salary as teacher_salary, " +
            "academic_status as teacher_academic_status from teachers where chairs_id = ?";

    private static final String TEACHER_ID = "teacher_id";
    private static final String TEACHER_SALARY = "teacher_salary";
    private static final String TEACHER_FIRST_NAME = "teacher_first_name";
    private static final String TEACHER_LAST_NAME = "teacher_last_name";
    private static final String TEACHER_BIRTHDAY = "teacher_birthday";
    private static final String TEACHER_ACADEMIC_STATUS = "teacher_academic_status";


    @Override
    public void create(Teacher teacher, Long addressesId, Long chairsId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setDate(3, Date.valueOf(teacher.getBirthday()));
            preparedStatement.setBigDecimal(4, teacher.getSalary());
            preparedStatement.setString(5, String.valueOf(teacher.getAcademicStatus()));
            preparedStatement.setLong(6, chairsId);
            preparedStatement.setLong(7, addressesId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                teacher.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to create new teacher record.", e);
            throw new CreateFailedException("Failed to create new teacher record.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        Teacher teacher = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = AddressDaoImpl.mapAddress(resultSet);
                teacher = mapTeacher(resultSet);
                teacher.setAddress(address);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find teacher with id " + id, e);
            throw new RetrieveInformationFailedException("Failed to find teacher with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(teacher);
    }

    @Override
    public void update(Teacher teacher, Long addressesId, Long chairsId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FULL_INFO)) {
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setDate(3, Date.valueOf(teacher.getBirthday()));
            preparedStatement.setBigDecimal(4, teacher.getSalary());
            preparedStatement.setString(5, String.valueOf(teacher.getAcademicStatus()));
            preparedStatement.setLong(6, addressesId);
            preparedStatement.setLong(7, chairsId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update teacher information.", e);
            throw new UpdateFailedException("Failed to update teacher information.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Teacher teacher) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, teacher.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to delete teacher information from DB with id " + teacher.getId(), e);
            throw new DeleteFailedException("Failed to delete teacher information from DB with id " + teacher.getId(), e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Teacher> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = AddressDaoImpl.mapAddress(resultSet);
                Teacher teacher = mapTeacher(resultSet);
                teacher.setAddress(address);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve teachers with addresses.", e);
            throw new RetrieveInformationFailedException("Failed to retrieve teachers with addresses.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return teachers;
    }

    public static Teacher mapTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong(TEACHER_ID));
        teacher.setFirstName((resultSet.getString(TEACHER_FIRST_NAME)));
        teacher.setLastName(resultSet.getString(TEACHER_LAST_NAME));
        teacher.setBirthday(LocalDate.parse(resultSet.getString(TEACHER_BIRTHDAY)));
        teacher.setSalary(resultSet.getBigDecimal(TEACHER_SALARY));
        teacher.setAcademicStatus(AcademicStatus.valueOf((resultSet.getString(TEACHER_ACADEMIC_STATUS))));
        return teacher;
    }
}
