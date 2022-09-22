package com.solvd.university.dao.impl;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.IFacultyDao;
import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.exception.CreateFailedException;
import com.solvd.university.domain.exception.DeleteFailedException;
import com.solvd.university.domain.exception.RetrieveInformationFailedException;
import com.solvd.university.domain.exception.UpdateFailedException;
import com.solvd.university.domain.university.Chair;
import com.solvd.university.domain.university.Faculty;
import com.solvd.university.domain.university.person.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyDaoImpl implements IFacultyDao {

    private static final Logger LOGGER = LogManager.getLogger(FacultyDaoImpl.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_FACULTY = "insert into faculties (name, universities_id, deans_id) values (?,?,?)";
    private static final String UPDATE_FACULTY_NAME = "update faculties set name = ? where id=?";
    private static final String DELETE_FACULTY = "delete from faculties where id = ?";
    private static final String FIND_FACULTY_INFO = "select f.id as faculty_id, f.name as faculty_name, c.id as chair_id," +
            " c.name as chair_name , t.first_name as teacher_first_name, a.city from faculties f " +
            "left join faculty_chairs fc on f.id=fc.faculty_id " +
            "left join chairs c on c.id=fc.chair_id " +
            "left join teachers t  on t.chairs_id = c.id " +
            "left join addresses a on t.addresses_id=a.id where f.id = ?";
    private static final String FIND_ALL = "select id as faculty_id, name as faculty_name from faculties";

    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_NAME = "faculty_name";

    @Override
    public void create(Faculty faculty, Long deansId, Long universitiesId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_FACULTY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, faculty.getName());
            preparedStatement.setLong(2, universitiesId);
            preparedStatement.setLong(3, deansId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to create new faculty", e);
            throw new CreateFailedException("Failed to create new faculty", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Faculty faculty, Long deansId, Long universitiesId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FACULTY_NAME)) {
            preparedStatement.setString(1, faculty.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update faculty information in DB", e);
            throw new UpdateFailedException("Failed to update faculty information in DB", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Faculty faculty) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FACULTY)) {
            preparedStatement.setLong(1, faculty.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to delete faculty from DB.", e);
            throw new DeleteFailedException("Failed to delete faculty from DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Faculty> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Faculty faculty = null;
        List<Chair> chairs = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_FACULTY_INFO)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();
                address.setCity(resultSet.getString("city"));
                Teacher teacher = new Teacher();
                teacher.setFirstName(resultSet.getString("teacher_first_name"));
                teacher.setAddress(address);
                teachers.add(teacher);
                Chair chair = ChairDaoImpl.mapChair(resultSet);
                chair.setTeachers(teachers);
                chairs.add(chair);
                faculty = mapFaculty(resultSet);
                faculty.setChairs(chairs);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve faculty information.", e);
            throw new RetrieveInformationFailedException("Failed to retrieve faculty information.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(faculty);
    }

    @Override
    public List<Faculty> findAll() {
        List<Faculty> faculties = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                faculties.add(mapFaculty(resultSet));
            }
        } catch (SQLException e) {
            throw new RetrieveInformationFailedException("Failed to retrieve all faculties.");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return faculties;
    }

    public static Faculty mapFaculty(ResultSet resultSet) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setId(resultSet.getLong(FACULTY_ID));
        faculty.setName(resultSet.getString(FACULTY_NAME));
        return faculty;
    }
}
