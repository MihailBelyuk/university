package com.solvd.university.dao.jdbcimpl;

import com.solvd.university.dao.ConnectionPool;
import com.solvd.university.dao.IAddressDao;
import com.solvd.university.domain.address.Address;
import com.solvd.university.domain.exception.CreateFailedException;
import com.solvd.university.domain.exception.DeleteFailedException;
import com.solvd.university.domain.exception.RetrieveInformationFailedException;
import com.solvd.university.domain.exception.UpdateFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements IAddressDao {

    private static final Logger LOGGER = LogManager.getLogger(AddressDaoImpl.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT_ADDRESS = "insert into addresses(city, street, house, flat, post_index) " +
            "values (?,?,?,?,?)";
    private static final String DELETE_ADDRESS = "delete from addresses where id=?";
    private static final String SHOW_ALL_ADDRESSES = "select id as address_id, city, street, house,flat,post_index " +
            "from addresses";
    private static final String UPDATE_ADDRESS = "update addresses set city=?, street=?, house=?, flat=?, post_index=? " +
            "where id=?";
    private static final String FIND_BY_ID = "select id as address_id,city,street,house,flat,post_index from addresses " +
            "where id=?";

    private static final String ADDRESS_ID = "address_id";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String FLAT = "flat";
    private static final String POST_INDEX = "post_index";

    @Override
    public void create(Address address) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setInt(3, address.getHouse());
            preparedStatement.setNull(4, Types.INTEGER);
            preparedStatement.setInt(5, address.getIndex());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                address.toBuilder().id(resultSet.getLong(1)).build();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to add new Address to the DB.");
            throw new CreateFailedException("Failed to add new Address to the DB.");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Address> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Address address = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address = mapAddress(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find address with id " + id, e);
            throw new RetrieveInformationFailedException("Failed to find address with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(address);
    }

    @Override
    public void update(Address address) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setInt(3, address.getHouse());
            preparedStatement.setInt(4, address.getFlat());
            preparedStatement.setInt(5, address.getIndex());
            preparedStatement.setLong(6, address.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update address.", e);
            throw new UpdateFailedException("Failed to update address.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Address address) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS)) {
            preparedStatement.setLong(1, address.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed too delete address from DB with id " + address.getId(), e);
            throw new DeleteFailedException("Failed too delete address from DB with id " + address.getId(), e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_ADDRESSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = mapAddress(resultSet);
                addresses.add(address);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to show all addresses.", e);
            throw new RetrieveInformationFailedException("Failed to show all addresses.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return addresses;
    }

    /**
     * Builder patten is used to create mutable object of Address
     * with less code and better readability.
     */
    public static Address mapAddress(ResultSet resultSet) throws SQLException {
        return Address.builder()
                .id(resultSet.getLong(ADDRESS_ID))
                .city(resultSet.getString(CITY))
                .street(resultSet.getString(STREET))
                .house(resultSet.getInt(HOUSE))
                .flat(resultSet.getInt(FLAT))
                .index(resultSet.getInt(POST_INDEX))
                .build();
    }
}
