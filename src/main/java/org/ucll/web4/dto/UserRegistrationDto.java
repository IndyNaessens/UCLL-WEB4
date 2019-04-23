package org.ucll.web4.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.ucll.web4.entity.UserEntity;
import org.ucll.web4.validator.password.PasswordFieldsMatch;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@PasswordFieldsMatch
public class UserRegistrationDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;

    @Min(12)
    @Max(110)
    private int age;

    @NotBlank
    private String gender;

    public UserRegistrationDto(String firstName, String lastName, String email, String password, String passwordConfirmation, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserEntity convertToEntity(){
        var passwordEncoder = new BCryptPasswordEncoder();

        return new UserEntity.Builder()
                .withRandomId()
                .withDefaultStatus()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email.toLowerCase())
                .hasAge(age)
                .withGender(gender)
                .withPassword(passwordEncoder.encode(password))
                .build();
    }
}
