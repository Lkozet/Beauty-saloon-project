package com.example.demo_beauty_saloon.dao;

import com.example.demo_beauty_saloon.db.DBManager;
import com.example.demo_beauty_saloon.entity.Service;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service entity data access object
 *
 */
public class ServiceDao {

    private static final String SQL_GET_SERVICES = "SELECT * FROM services where availability = 'true'";

    private Logger log = Logger.getLogger(ServiceDao.class);

    /**
     * Returns list of services
     * @return List of services
     */
    public List<Service> selectAllServices() {
        log.debug("Getting Services List");
        List<Service> serviceList = new ArrayList<>();
        Service service = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            ServiceMapper mapper = new ServiceMapper();
            preparedStatement = connection.prepareStatement(SQL_GET_SERVICES);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                service = mapper.mapRow(resultSet);
                serviceList.add(service);
            }
        } catch (SQLException e) {
            log.error("ServiceDao.selectAllServices() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
            return null;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("List acquired");
        return serviceList;
    }

    /**
     * Extracts a service from the result set row.
     */
    private static class ServiceMapper {

        public Service mapRow(ResultSet resultSet) {
            try {
                Service service = new Service();
                service.setId(resultSet.getLong("serviceid"));
                service.setNameEn(resultSet.getString("nameEn"));
                service.setNameRu(resultSet.getString("nameRu"));
                service.setDescriptionEn(resultSet.getString("descriptionEn"));
                service.setDescriptionRu(resultSet.getString("descriptionRu"));
                service.setTimeslots(resultSet.getString("timeslots"));
                service.setAvailability(resultSet.getString("availability"));
                service.setPrice(resultSet.getDouble("price"));

                return service;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
