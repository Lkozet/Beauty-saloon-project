package com.example.demo_beauty_saloon.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

/**
 * Class for getting database connection
 *
 */
public class DBManager {

    private static final Logger log = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Configure TomCat connection pool
     *
     */
    private static BasicDataSource dataSource = null;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3336/beauty_saloon?serverTimezone=UTC&useSSL=false&autocommit=false");
        dataSource.setUsername("root");
        dataSource.setPassword("5689root9865");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMaxWaitMillis(10000);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
        dataSource.setDefaultAutoCommit(false);
        dataSource.setTestWhileIdle(false);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnReturn(false);
        dataSource.setTimeBetweenEvictionRunsMillis(30000);
        dataSource.setInitialSize(10);
        dataSource.setRemoveAbandonedTimeout(60);
        dataSource.setMinEvictableIdleTimeMillis(30000);
        dataSource.setMinIdle(10);
        dataSource.setLogAbandoned(true);
        log.info("DataSource initialized");
    }


    /**
     * Returns a DB connection from the Connection Pool.
     *
     * @return A DB connection.
     */
    public Connection getConnection() throws SQLException {
        log.info("Getting connection");
        Connection connection = null;
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        log.info("Connection acquired");
        return connection;
    }

    /**
     * Commits and close the given connection.
     *
     * @param connection Connection to be committed and closed.
     */
    public void commitAndClose(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param connection Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }
    /**
     * Close the given preparedStatement.
     *
     * @param preparedStatement PreparedStatement to be closed.
     */
    public void closePS(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }

    /**
     * Close the given resultSet.
     *
     * @param resultSet ResultSet to be closed.
     */
    public void closeRS(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }

    /**
     * Close the given statement.
     *
     * @param statement Statement to be closed.
     */
    public void closeS(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }
}