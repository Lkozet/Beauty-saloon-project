import com.example.demo_beauty_saloon.validator.Validator;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {
    static Validator validator;

    @Test
    public void validateNameTest(){
        validator = new Validator();
        assertTrue(validator.isNameValid("Michael"));
        assertFalse(validator.isNameValid("12345"));
    }

    @Test
    public void validateEmailTest(){
        validator = new Validator();
        assertTrue(validator.isEmailValid("Michael@test.com"));
        assertFalse(validator.isEmailValid("12345"));
    }

    @Test
    public void validatePhoneTest(){
        validator = new Validator();
        assertTrue(validator.isPhoneNumberValid("+380955119244"));
        assertFalse(validator.isPhoneNumberValid("Michael"));
        assertFalse(validator.isPhoneNumberValid("+38095"));
    }

    @Test
    public void validatePasswordTest(){
        validator = new Validator();
        assertTrue(validator.isPasswordValid("12345678"));
        assertFalse(validator.isPasswordValid("/#Michael#/"));
    }
}
