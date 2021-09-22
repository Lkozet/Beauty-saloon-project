package com.example.demo_beauty_saloon.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Validator for input data
 *
 */
public class Validator {

    private static final Logger log = Logger.getLogger(Validator.class);

    /**
     * Checks if password is valid
     * @param pass
     * @return true - valid; false invalid
     */
    public boolean isPasswordValid(String pass) {
        if (pass == null || pass.isEmpty()) {
            log.info("Incorrect password input " + pass);
            return false;
        }
        Pattern pattern = Pattern.compile("^[а-яА-Я\\wіІїЇ@#$%^&+=]{6,64}$");
        Matcher matcher = pattern.matcher(pass);
        if (!matcher.matches()) {
            log.info("Incorrect password input " + pass);
            return false;
        }
        log.info("Correct password input " + pass);
        return true;
    }

    /**
     * Checks if username is valid
     * @param name
     * @return true - valid; false invalid
     */
    public boolean isNameValid(String name){
        if (name == null || name.isEmpty()) {
            log.info("Incorrect name input " + name);
            return false;
        }
        Pattern pattern = Pattern.compile("^[A-Za-zА-ЯІЇа-яії ]{3,20}$");
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            log.info("Incorrect name input " + name);
            return false;
        }
        log.info("Correct name input " + name);
        return true;
    }

    /**
     * Checks if phone number is valid
     * @param phone
     * @return true - valid; false invalid
     */
    public boolean isPhoneNumberValid(String phone){
        if (phone == null || phone.isEmpty()) {
            log.info("Incorrect phone input " + phone);
            return false;
        }
        Pattern pattern = Pattern.compile("^[+][0-9]{10,13}$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            log.info("Incorrect phone input " + phone);
            return false;
        }
        log.info("Correct phone input " + phone);
        return true;
    }

    /**
     * Checks if email address is valid
     * @param email
     * @return true - valid; false invalid
     */
    public boolean isEmailValid(String email){
        if (email == null || email.isEmpty()) {
            log.info("Incorrect email input " + email);
            return false;
        }
        Pattern pattern = Pattern.compile("^.{3,25}@.{3,25}$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            log.info("Incorrect email input " + email);
            return false;
        }
        log.info("Correct email input " + email);
        return true;
    }
}
