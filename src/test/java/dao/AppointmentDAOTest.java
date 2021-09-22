package dao;

import com.example.demo_beauty_saloon.dao.AppointmentDao;
import com.example.demo_beauty_saloon.entity.Appointment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AppointmentDAOTest {
    static AppointmentDao appointmentDao;
    static Appointment appointment;

    @Test
    public void registerAndDeleteAppTest() {
        appointmentDao = new AppointmentDao();
        appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setMasterId(1);
        appointment.setServiceId(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse("2021-08-03 11:00:00", formatter);
        appointment.setTimestamp(timeStamp);
        appointment.setPaymentStatus("waiting");
        appointmentDao.registerApp(appointment);
        Appointment dbApp = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.deleteApp(dbApp.getAppId());
        assertNotNull(dbApp);
    }

    @Test
    public void updateAppTimestampTest() {
        appointmentDao = new AppointmentDao();
        appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setMasterId(1);
        appointment.setServiceId(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse("2021-08-03 10:00:00", formatter);
        LocalDateTime newTimeStamp = LocalDateTime.parse("2021-08-03 12:00:00", formatter);
        appointment.setTimestamp(timeStamp);
        appointment.setPaymentStatus("waiting");
        appointmentDao.registerApp(appointment);
        Appointment dbApp = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.updateAppTimestamp(dbApp.getAppId(), newTimeStamp);
        Appointment dbAppNewTimestamp = appointmentDao.findAppByTime(newTimeStamp);
        appointmentDao.deleteApp(dbApp.getAppId());
        assertNotEquals(dbApp.getTimestamp(), dbAppNewTimestamp.getTimestamp());
    }

    @Test
    public void updateAppCommentTest() {
        appointmentDao = new AppointmentDao();
        appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setMasterId(1);
        appointment.setServiceId(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse("2021-08-03 11:00:00", formatter);
        appointment.setTimestamp(timeStamp);
        appointment.setPaymentStatus("waiting");
        appointmentDao.registerApp(appointment);
        Appointment dbApp = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.updateAppComment(dbApp.getAppId(), "test");
        Appointment dbAppNewComment = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.deleteApp(dbApp.getAppId());
        assertNotEquals(dbApp.getComment(), dbAppNewComment.getComment());
    }

    @Test
    public void updateAppStatusTest() {
        appointmentDao = new AppointmentDao();
        appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setMasterId(1);
        appointment.setServiceId(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse("2021-08-03 13:00:00", formatter);
        appointment.setTimestamp(timeStamp);
        appointment.setPaymentStatus("waiting");
        appointmentDao.registerApp(appointment);
        Appointment dbApp = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.updateAppStatus(dbApp.getAppId(), "test");
        Appointment dbAppNewComment = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.deleteApp(dbApp.getAppId());
        assertNotEquals(dbApp.getStatus(), dbAppNewComment.getStatus());
    }

    @Test
    public void updateAppPaymentStatusTest() {
        appointmentDao = new AppointmentDao();
        appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setMasterId(1);
        appointment.setServiceId(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse("2021-08-03 14:00:00", formatter);
        appointment.setTimestamp(timeStamp);
        appointment.setPaymentStatus("waiting");
        appointmentDao.registerApp(appointment);
        Appointment dbApp = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.updateAppPaymentStatus(dbApp.getAppId(), "test");
        Appointment dbAppNewComment = appointmentDao.findAppByTime(timeStamp);
        appointmentDao.deleteApp(dbApp.getAppId());
        assertNotEquals(dbApp.getPaymentStatus(), dbAppNewComment.getPaymentStatus());
    }

    @Test
    public void AppByMIdTest() {
        appointmentDao = new AppointmentDao();
        appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setMasterId(1);
        appointment.setServiceId(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse("2021-08-03 15:00:00", formatter);
        appointment.setTimestamp(timeStamp);
        appointment.setPaymentStatus("waiting");
        ArrayList<Appointment> prevDbApps = appointmentDao.findAppByMId(appointment.getMasterId());
        appointmentDao.registerApp(appointment);
        ArrayList<Appointment> dbApp = appointmentDao.findAppByMId(appointment.getMasterId());
        appointmentDao.deleteApp(appointmentDao.findAppByTime(timeStamp).getAppId());
        assertNotEquals(prevDbApps.size(), dbApp.size());
    }

    @Test
    public void AppByUIdTest() {
        appointmentDao = new AppointmentDao();
        appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setMasterId(1);
        appointment.setServiceId(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse("2021-08-03 16:00:00", formatter);
        appointment.setTimestamp(timeStamp);
        appointment.setPaymentStatus("waiting");
        ArrayList<Appointment> prevDbApps = appointmentDao.findAppByUId(appointment.getUserId());
        appointmentDao.registerApp(appointment);
        ArrayList<Appointment> dbApp = appointmentDao.findAppByUId(appointment.getUserId());
        appointmentDao.deleteApp(appointmentDao.findAppByTime(timeStamp).getAppId());
        assertNotEquals(prevDbApps.size(), dbApp.size());
    }
}
