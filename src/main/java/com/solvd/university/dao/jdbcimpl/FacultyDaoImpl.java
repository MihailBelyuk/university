package com.solvd.university.dao.jdbcimpl;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.IFacultyDao;
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

    private static final String CREATE_FACULTY = "insert into faculties (name, universities_id, deans_id) values (?, ?, ?)";
    private static final String UPDATE_FACULTY_NAME = "update faculties set name = ? where id = ?";
    private static final String DELETE_FACULTY = "delete from faculties where id = ?";
    private static final String FIND_FACULTY_INFO = "select f.id as faculty_id, f.name as faculty_name, " +
            "c.id as chair_id, c.name as chair_name, t.id as teacher_id, t.first_name as teacher_first_name, " +
            "t.last_name as teacher_last_name, t.birthday as teacher_birthday, t.salary as teacher_salary, " +
            "t.academic_status as teacher_academic_status from faculties f " +
            "left join faculty_chairs fc on f.id = fc.faculty_id " +
            "left join chairs c on fc.chair_id=c.id " +
            "left join teachers t on t.chairs_id = c.id where f.id = ?";
    private static final String FIND_ALL = "select id as faculty_id, name as faculty_name from faculties";

    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_NAME = "faculty_name";
    private static final String CHAIR_NAME = "chair_name";
    private static final String CHAIR_ID = "chair_id";

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
        Chair chair = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_FACULTY_INFO)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                faculty = mapFaculty(resultSet);
                Chair current = mapChairs(resultSet);
                Teacher teacher = TeacherDaoImpl.mapTeacher(resultSet);
                chair = getChair(chairs, chair, current, teacher);
            }
            chairs.add(chair);
            if (faculty != null) {
                faculty = Faculty.builder().chairs(chairs).build();
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

    /**
     * Builder patten is used to create mutable object of Faculty
     * with less code and better readability.
     */
    public static Faculty mapFaculty(ResultSet resultSet) throws SQLException {
        List<Chair> chairs = new ArrayList<>();
        return Faculty.builder()
                .chairs(chairs)
                .id(resultSet.getLong(FACULTY_ID))
                .name(FACULTY_NAME).build();
    }

    /**
     * Builder patten is used to create mutable object of Chair
     * with less code and better readability.
     */
    public static Chair mapChairs(ResultSet resultSet) throws SQLException {
        return Chair.builder()
                .name(resultSet.getString(CHAIR_NAME))
                .id(resultSet.getLong(CHAIR_ID))
                .teachers(new ArrayList<>())
                .build();
    }

    private Chair getChair(List<Chair> chairs, Chair chair, Chair current, Teacher teacher) {
        if (chair == null) {
            current.getTeachers().add(teacher);
            chair = current;
        } else if (current.getId().equals(chair.getId())) {
            chair.getTeachers().add(teacher);
        } else {
            chairs.add(chair);
            current.getTeachers().add(teacher);
            chair = current;
        }
        return chair;
    }
}


