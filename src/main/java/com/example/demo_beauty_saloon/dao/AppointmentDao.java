package com.example.demo_beauty_saloon.dao;

import com.example.demo_beauty_saloon.db.DBManager;
import com.example.demo_beauty_saloon.entity.Appointment;

import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Appointment entity data access object
 *
 */
public class AppointmentDao {
    private static final String SQL_INSERT_APPOINTMENT_SQL = "INSERT INTO appointments" +
            "  (userId, masterId, serviceId, timestamp, paymentStatus, status, emailNeeded) VALUES " +
            " (?, ?, ?, ?, ?, 'waiting', 'true');";
    private static final String SQL_UPDATE_APPOINTMENT_COMMENT_SQL = "UPDATE appointments " +
            "SET comment = ? WHERE appointmentid = ?";
    private static final String SQL_GET_APPOINTMENT_BY_TIME_SQL = "SELECT * FROM appointments " +
            "WHERE timestamp = ? ";
    private static final String SQL_GET_APPOINTMENTS = "SELECT * FROM appointments ";
    private static final String SQL_FIND_APPOINTMENT_BY_USERID = "SELECT * FROM appointments WHERE userId = ?";
    private static final String SQL_FIND_APPOINTMENT_BY_MASTERID = "SELECT * FROM appointments WHERE masterId = ?";
    private static final String SQL_DELETE_APPOINTMENT = "DELETE FROM appointments WHERE appointmentid = ?";
    private static final String SQL_UPDATE_APPOINTMENT_TIMESTAMP = "UPDATE appointments SET timestamp = ? " +
            "WHERE appointmentid = ?";
    private static final String SQL_UPDATE_APPOINTMENT_PAYMENT_STATUS = "UPDATE appointments SET paymentStatus = ? " +
            "WHERE appointmentid = ?";
    private static final String SQL_UPDATE_APPOINTMENT_STATUS = "UPDATE appointments SET status = ? " +
            "WHERE appointmentid = ?";
    private static final String SQL_UPDATE_APPOINTMENT_EMAIL_NEEDED = "UPDATE appointments SET emailNeeded = ? " +
            "WHERE appointmentid = ?";

    private static final Logger log = Logger.getLogger(AppointmentDao.class);

    /**
     * Register new Appointment.
     *
     * @param appointment
     * @return int.
     */
    public int registerApp(Appointment appointment) {
        log.info("Registration starts");
        int result = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_INSERT_APPOINTMENT_SQL);
            preparedStatement.setLong(1, appointment.getUserId());
            preparedStatement.setLong(2, appointment.getMasterId());
            preparedStatement.setLong(3, appointment.getServiceId());
            preparedStatement.setObject(4, appointment.getTimestamp());
            preparedStatement.setObject(5, appointment.getPaymentStatus());

            log.info(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            log.error("AppointmentDao.registerApp() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return result;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.info("Registration success");
        return result;
    }

    /**
     * Update appointment comment by id
     * @param comment comment to update
     * @param appid appointment id
     * @return int : 0 = error, 1 = success
     */
    public int updateAppComment(long appid, String comment) {
        log.debug("Updating Appointment: " + appid + " comment");
        int result = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APPOINTMENT_COMMENT_SQL);
            preparedStatement.setString(1, comment);
            preparedStatement.setLong(2, appid);

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            log.error("AppointmentDao.updateAppComment() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return result;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.info("Update success");
        return result;
    }

    /**
     * Update appointment email needed by id
     * @param emailNeeded comment to update
     * @param appid appointment id
     */
    public void updateAppEmailNeeded(long appid, String emailNeeded) {
        log.debug("Updating Appointment: " + appid + " emailNeeded");
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APPOINTMENT_EMAIL_NEEDED);
            preparedStatement.setString(1, emailNeeded);
            preparedStatement.setLong(2, appid);

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            log.error("AppointmentDao.updateAppEmailNeeded() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.info("Update success");
    }

    public ArrayList<Appointment> findAppByUId(long userId) {
        return getAppointments(userId, SQL_FIND_APPOINTMENT_BY_USERID);
    }

    public ArrayList<Appointment> findAppByMId(long masterId) {
        return getAppointments(masterId, SQL_FIND_APPOINTMENT_BY_MASTERID);
    }

    /**
     * Get appointments by userId/masterId
     * @param sqlFindAppointments sql code specific to role
     * @param userId user id
     * @return List of appointments
     */
    private ArrayList<Appointment> getAppointments(long userId, String sqlFindAppointments) {
        log.debug("Getting Appointments");
        ArrayList<Appointment> apps = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            AppointmentMapper mapper = new AppointmentMapper();
            preparedStatement = connection.prepareStatement(sqlFindAppointments);
            preparedStatement.setLong(1, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                apps.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            log.error("AppointmentDao.getAppointments() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
            return apps;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.info("Getting Appointments success");
        return apps;
    }

    /**
     * Update appointment payment status
     * @param appid appointment id
     * @param paymentStatus payment status to set instead of old
     * @return int : 0 = error, 1 = success
     */
    public int updateAppPaymentStatus(long appid, String paymentStatus) {
        log.debug("Updating Appointment payment status");
        int res = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APPOINTMENT_PAYMENT_STATUS);
            preparedStatement.setObject(1, paymentStatus);
            preparedStatement.setLong(2, appid);

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            res = preparedStatement.executeUpdate();
            System.out.println(res);
        } catch (SQLException e) {
            log.error("AppointmentDao.updateAppPaymentStatus() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return res;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Update success");
        return res;
    }

    /**
     * Update appointment payment status
     * @param appid appointment id
     * @param status payment status to set instead of old
     * @return int : 0 = error, 1 = success
     */
    public int updateAppStatus(long appid, String status) {
        log.debug("Updating Appointment status");
        int res = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APPOINTMENT_STATUS);
            preparedStatement.setObject(1, status);
            preparedStatement.setLong(2, appid);

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            res = preparedStatement.executeUpdate();
            System.out.println(res);
        } catch (SQLException e) {
            log.error("AppointmentDao.updateAppStatus() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return res;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Update success");
        return res;
    }

    /**
     * Update appointment timestamp
     * @param appid appointment id
     * @param timestamp timestamp to set instead of old
     * @return int : 0 = error, 1 = success
     */
    public int updateAppTimestamp(long appid, LocalDateTime timestamp) {
        log.debug("Updating Appointment timestamp");
        int result = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APPOINTMENT_TIMESTAMP);
            preparedStatement.setObject(1, timestamp);
            preparedStatement.setLong(2, appid);

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            log.error("AppointmentDao.updateAppTimestamp() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return result;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Update success");
        return result;
    }

    /**
     * Delete appointment
     * @param appid appointment id
     * @return int : 0 = error, 1 = success
     */
    public int deleteApp(long appid) {
        log.debug("Deleting appointment");
        int result = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_DELETE_APPOINTMENT);
            preparedStatement.setLong(1, appid);

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            log.error("AppointmentDao.deleteApp() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return result;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Delete success");
        return result;
    }

    /**
     * Get appointments
     * @return List of appointments
     */
    public ArrayList<Appointment> getAllAppointments() {
        log.debug("Getting list of Appointments");
        ArrayList<Appointment> apps = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            AppointmentMapper mapper = new AppointmentMapper();
            preparedStatement = connection.prepareStatement(SQL_GET_APPOINTMENTS);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                apps.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            log.error("AppointmentDao.getAllAppointments() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
            return apps;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("Lis acquired");
        return apps;
    }

    /**
     * Get appointment by Time
     * @return Appointment
     */
    public Appointment findAppByTime(LocalDateTime timeStamp) {
        log.debug("Searching Appointment by time");
        Appointment app = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            AppointmentMapper mapper = new AppointmentMapper();
            preparedStatement = connection.prepareStatement(SQL_GET_APPOINTMENT_BY_TIME_SQL);
            preparedStatement.setObject(1, timeStamp);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                app = (mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            log.error("AppointmentDao.findAppByTime() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
            return app;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("Search success");
        return app;
    }

    /**
     * Extracts an appointment from the result set row.
     */
    private static class AppointmentMapper {

        public Appointment mapRow(ResultSet resultSet) {
            try {
                Appointment appointment = new Appointment();
                appointment.setAppId(resultSet.getLong("appointmentid"));
                appointment.setUserId(resultSet.getLong("userId"));
                appointment.setMasterId(resultSet.getLong("masterId"));
                appointment.setServiceId(resultSet.getLong("serviceId"));
                appointment.setTimestamp(resultSet.getObject("timestamp", LocalDateTime.class));
                appointment.setComment(resultSet.getString("comment"));
                appointment.setPaymentStatus(resultSet.getString("paymentStatus"));
                appointment.setStatus(resultSet.getString("status"));
                appointment.setEmailNeeded(resultSet.getString("emailNeeded"));

                return appointment;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
