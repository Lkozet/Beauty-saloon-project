package com.example.demo_beauty_saloon.dao;

import com.example.demo_beauty_saloon.db.DBManager;
import com.example.demo_beauty_saloon.entity.Master;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Master entity data access object
 */
public class MasterDao {

    private static final String SQL_INSERT_MASTER = "INSERT INTO masters" +
            "  (login , password, nameEn, nameRu, rating, informationEn, informationRu, serviceId) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_GET_MASTER_BY_LOGIN = "SELECT * FROM masters WHERE login = ?";
    private static final String SQL_GET_MASTERS = "SELECT * FROM masters";
    private static final String SQL_GET_MASTERS_BY_SERVICEID = "SELECT * FROM masters WHERE serviceId = ?";

    private Logger log = Logger.getLogger(MasterDao.class);

    /**
     * Register new Master.
     *
     * @param master
     * @return int.
     */
    public int registerMaster(Master master) {
        log.debug("Registering master");
        int result = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_INSERT_MASTER);
            preparedStatement.setString(1, master.getLogin());
            preparedStatement.setString(2, master.getPassword());
            preparedStatement.setString(3, master.getNameEn());
            preparedStatement.setString(4, master.getNameRu());
            preparedStatement.setDouble(5, master.getRating());
            preparedStatement.setString(6, master.getInformationEn());
            preparedStatement.setString(7, master.getInformationRu());
            preparedStatement.setLong(8, master.getServiceId());

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            log.error("AppointmentDao.registerMaster() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return result;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Register success");
        return result;
    }

    /**
     * Returns list of masters
     *
     * @return List of masters
     */
    public List<Master> selectAllMasters() {
        log.debug("Getting Masters List");
        List<Master> masterList = new ArrayList<>();
        Master master;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.prepareStatement(SQL_GET_MASTERS);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                master = mapper.mapRow(resultSet);
                masterList.add(master);
            }
        } catch (SQLException e) {
            log.error("MasterDao.selectAllMasters() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
            return null;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("List acquired");
        return masterList;
    }

    /**
     * Returns list of masters by service(serviceId) they provide
     *
     * @param serviceId
     * @return List of masters
     */
    public List<Master> selectMastersByServiceId(long serviceId) {
        log.debug("Getting Masters by serviceId");
        List<Master> masterList = new ArrayList<>();
        Master master;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.prepareStatement(SQL_GET_MASTERS_BY_SERVICEID);
            preparedStatement.setLong(1, serviceId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                master = mapper.mapRow(resultSet);
                masterList.add(master);
            }
        } catch (SQLException e) {
            log.error("MasterDao.selectMastersByServiceId() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
            return null;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("List acquired");
        return masterList;
    }

    /**
     * Delete master
     *
     */
    public void deleteMaster(long masterid) {
        log.debug("Deleting master");
        String sql = "DELETE FROM masters WHERE masterid = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();

        try {
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, masterid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("MasterDao.deleteMaster() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Delete success");
    }

    /**
     * Find masterId by login
     *
     * @param login master login
     * @return long masterId
     */
    public long findMasterIdByLogin(String login) {
        log.debug("Searching Master by login");
        Master master = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.prepareStatement(SQL_GET_MASTER_BY_LOGIN);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                master = mapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            log.error("MasterDao.findMasterIdByLogin() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        if (master != null) {
            log.debug("Search success");
            return master.getId();
        }
        return 0;
    }

    /**
     * Extracts a master from the result set row.
     */
    private static class MasterMapper {

        public Master mapRow(ResultSet resultSet) {
            try {
                Master master = new Master();
                master.setId(resultSet.getLong("masterid"));
                master.setLogin(resultSet.getString("login"));
                master.setPassword(resultSet.getString("password"));
                master.setNameEn(resultSet.getString("nameEn"));
                master.setNameRu(resultSet.getString("nameRu"));
                master.setRating(resultSet.getDouble("rating"));
                master.setInformationEn(resultSet.getString("informationEn"));
                master.setInformationRu(resultSet.getString("informationRu"));
                master.setServiceId(resultSet.getLong("serviceId"));

                return master;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
