package com.example.demo_beauty_saloon.dao;

import com.example.demo_beauty_saloon.bean.LoginBean;
import com.example.demo_beauty_saloon.db.DBManager;
import com.example.demo_beauty_saloon.entity.User;
import com.example.demo_beauty_saloon.validator.Validator;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Web-application User entity data access object
 *
 */
public class UserDao {

    private static final String SQL_INSERT_USERS_SQL = "INSERT INTO users" +
            "  (name, email, password, phone, role) VALUES " +
            " (?, ?, ?, ?, 'client');";
    private static final String SQL_GET_USER_SQL = "select * from users where name = ? and password = ? ";
    private static final String SQL_GET_USER_ROLE_SQL = "select role from users " +
            "where name = ? and password = ? ";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE name = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE userid = ?";

    private Logger log = Logger.getLogger(UserDao.class);

    /**
     * Registers user with provided data
     * @param user User data
     * @return int : 0 = error, 1 = success
     */
    public int registerUser(User user) {
        log.debug("Registering User");
        int result = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone());

            log.debug(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("UserDao.registerUser() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return result;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Registration success");
        return result;
    }

    /**
     * Checks if user has an account
     * @param loginBean user password and login
     * @return boolean
     */
    public boolean validate(LoginBean loginBean){
        log.debug("Searching User: " + loginBean.getUsername() + " in Database");
        boolean status;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_GET_USER_SQL);
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            log.debug(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            log.error("UserDao.validate() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            return false;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Search success");
        return status;
    }

    /**
     * Finds user by login if exists
     * @param loginBean UserBean login
     * @return String role
     */
    public String getUserRole(LoginBean loginBean){
        log.debug("Getting " + loginBean.getUsername() + " role");
        String result = null;
        String role_id = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SQL_GET_USER_ROLE_SQL);
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());
            log.debug(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                log.debug(resultSet.getString("role"));
                role_id = resultSet.getString("role");
            }
            if (role_id != null) {
                switch (role_id) {
                    case "client":
                        result = "client";
                        break;
                    case "master":
                        result = "master";
                        break;
                    case "admin":
                        result = "admin";
                        break;
                }
            }
        } catch (SQLException e) {
            log.error("UserDao.getUserRole() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
            return null;
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("Role acquired");
        return result;
    }

    /**
     * Finds user by login if exists
     * @param login User login
     * @return User entity
     */
    public User findUserByLogin(String login) {
        log.debug("Searching user by Login:" + login);
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            UserMapper mapper = new UserMapper();

            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapper.mapRow(resultSet);
            }
        } catch (SQLException ex) {
            log.error("UserDao.findUserByLogin() failed: ", ex);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("Search success");
        return user;
    }

    /**
     * Get User by UId
     *
     * @param id
     * @return User
     */
    public User findUserByUId(long id) {
        log.debug("Searching user by ID:" + id);
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();
        try {
            connection = db.getConnection();
            UserMapper mapper = new UserMapper();

            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapper.mapRow(resultSet);
            }
        } catch (SQLException ex) {
            log.error("UserDao.findUserByUId() failed: ", ex);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
            db.closeRS(resultSet);
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        db.closeRS(resultSet);
        log.debug("Search success");
        return user;
    }

    /**
     * Delete user
     *
     */
    public void deleteUser(User user) {
        log.debug("Deleting user" + user.getName());
        String sql = "DELETE FROM users WHERE userid = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBManager db = DBManager.getInstance();

        try {
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();

            log.debug("User " + user.getName() + " has been deleted");
        } catch (SQLException e) {
            log.error("UserDao.deleteUser() failed: ", e);
            db.rollbackAndClose(connection);
            db.closePS(preparedStatement);
        }
        db.commitAndClose(connection);
        db.closePS(preparedStatement);
        log.debug("Delete success");
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class UserMapper {

        public User mapRow(ResultSet resultSet) {
            try {
                User user = new User();
                user.setId(resultSet.getLong("userid"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
                user.setRole(resultSet.getString("role"));

                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
