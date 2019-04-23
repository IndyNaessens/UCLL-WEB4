package org.ucll.web4.entity;

import java.util.Objects;
import java.util.UUID;


public class UserEntity {

    private final UUID userId; //unique
    private final String firstName;
    private final String lastName;
    private final String email; //unique
    private final String password;
    private final int age;
    private final String gender;
    private String status;

    //constructors
    private UserEntity(UUID id, String firstName, String lastName, String email, String password, int age, String gender) {
        this.userId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    //getters
    public UUID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    //setters
    public void setStatus(String status) {
        this.status = status;
    }

    //equals and hashcode
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserEntity)) return false;

        UserEntity temp = (UserEntity) object;
        return temp.getUserId().equals(getUserId()) &&
                temp.getEmail().equals(getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getEmail());
    }

    //builder
    public static class Builder {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private int age;
        private String gender;
        private String status;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withRandomId() {
            this.id = UUID.randomUUID();
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withDefaultStatus() {
            status = "Offline";
            return this;
        }

        public Builder isMale(){
            this.gender = "Male";
            return this;
        }

        public Builder isFemale(){
            this.gender = "Female";
            return this;
        }

        public Builder withGender(String gender){
            this.gender = gender;
            return this;
        }

        public Builder hasAge(int age){
            this.age = age;
            return this;
        }

        public UserEntity build() {
            var user = new UserEntity(id, firstName, lastName, email, password, age, gender);
            user.setStatus(status);

            return user;
        }
    }
}
