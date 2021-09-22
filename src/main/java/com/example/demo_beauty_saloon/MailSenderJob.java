package com.example.demo_beauty_saloon;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Test class for Quartz Scheduler
 */
public class MailSenderJob implements Job {

    Logger log = Logger.getLogger(MailSenderJob.class);

    //TODO: this job sends emails every  10 sec (can be changed to 1 day as in task)
    // (currently doesn't work: working Email needed; previously tested (works fine))
    @Override
    public void execute(JobExecutionContext pArg0) throws JobExecutionException {
        log.info("The mail sender job triggered");
        //From here call the mail sender service

        /*try {

            System.out.println("Job Email Runnning");
            final String username = "youremail@test.com";
            final String from = "youremail@test.com";
            final String password = "yourpass";

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.yourhost.com"); // smtp.gmail.com
            props.put("mail.smtp.port", "465"); // 465/587

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            System.out.println("Job Email Running 2");
            AppointmentDao appointmentDao = new AppointmentDao();
            UserDao userDao = new UserDao();
            ArrayList<Appointment> allAppointments = appointmentDao.getAllAppointments();
            // Sending email

            for (Appointment app : allAppointments) {
                if (app.getTimestamp().plusDays(1).isAfter(LocalDateTime.now())
                        && app.getEmailNeeded().equals("true")) {
                    try {
                        Message message = new MimeMessage(session);

                        message.setFrom(new InternetAddress(from));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(userDao.findUserByUId(app.getUserId()).getEmail()));
                        message.setSubject("Test Email");
                        String body = "Dear User Recipients"
                                + "<br>Just test email";

                        message.setContent(body, "text/html; charset=utf-8");
                        Transport.send(message);

                        System.out.println("Sending Email for user: "
                                + userDao.findUserByUId(app.getUserId()).getName()
                                + "; timestamp: "+ app.getTimestamp() + ". Done");

                    } catch (MessagingException e) {
                        throw new RuntimeException("Sending Email for user: "
                                + userDao.findUserByUId(app.getUserId()).getName()
                                + "; timestamp: "+ app.getTimestamp() + ". Error", e);
                    }
                    appointmentDao.updateAppEmailNeeded(app.getAppId(), "false");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

}