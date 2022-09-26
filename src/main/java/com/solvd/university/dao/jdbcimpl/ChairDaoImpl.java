package com.solvd.university.dao.jdbcimpl;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.IChairDao;
import com.solvd.university.domain.exception.CreateFailedException;
import com.solvd.university.domain.exception.DeleteFailedException;
import com.solvd.university.domain.exception.RetrieveInformationFailedException;
import com.solvd.university.domain.exception.UpdateFailedException;
import com.solvd.university.domain.university.Chair;
import com.solvd.university.domain.university.person.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChairDaoImpl implements IChairDao {

    private static final Logger LOGGER = LogManager.getLogger(ChairDaoImpl.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_CHAIR = "insert into chairs(name) values (?)";
    private static final String FIND_BY_ID = "select c.id as chair_id, c.name as chair_name, t.id as teacher_id, " +
            "t.first_name as teacher_first_name, t.last_name as teacher_last_name from chairs c " +
            "left join teachers t on t.chairs_id = c.id where c.id = ?";
    private static final String UPDATE = "update chairs set name = ? where id = ?";
    private static final String DELETE = "delete from chairs where id = ?";
    private static final String FIND_ALL = "select id as chair_id, name as chair_name from chairs";

    private static final String CHAIR_ID = "chair_id";
    private static final String CHAIR_NAME = "chair_name";
    private static final String TEACHER_ID = "teacher_id";
    private static final String TEACHER_FIRST_NAME = "teacher_first_name";
    private static final String TEACHER_LAST_NAME = "teacher_last_name";

    @Override
    public void create(Chair chair) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CHAIR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, chair.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                chair.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to add new chair to the DB.", e);
            throw new CreateFailedException("Failed to add new chair to the DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Chair> findById(Long id) {
        Chair chair = null;
        List<Teacher> teachers = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chair = mapChair(resultSet);
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong(TEACHER_ID));
                teacher.setFirstName(resultSet.getString(TEACHER_FIRST_NAME));
                teacher.setLastName((resultSet.getString(TEACHER_LAST_NAME)));
                teachers.add(teacher);
                chair.setTeachers(teachers);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find chair with id " + id, e);
            throw new RetrieveInformationFailedException("Failed to find chair with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(chair);
    }

    @Override
    public void update(Chair chair) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, chair.getName());
            preparedStatement.setLong(2, chair.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update chair information in DB.", e);
            throw new UpdateFailedException("Failed to update chair information in DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Chair chair) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, chair.getId());
        } catch (SQLException e) {
            LOGGER.error("Failed to delete chair data from DB with id " + chair.getId(), e);
            throw new DeleteFailedException("Failed to delete chair data from DB with id " + chair.getId(), e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Chair> findAll() {
        List<Chair> chairs = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Chair chair = mapChair(resultSet);
                chairs.add(chair);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve all chairs information from DB.", e);
            throw new RetrieveInformationFailedException("Failed to retrieve all chairs information from DB.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return chairs;
    }

    public static Chair mapChair(ResultSet resultSet) throws SQLException {
        Chair chair = new Chair();
        chair.setId(resultSet.getLong(CHAIR_ID));
        chair.setName(resultSet.getString(CHAIR_NAME));
        return chair;
    }
}
