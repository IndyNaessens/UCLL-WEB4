package org.ucll.web4.chat_user;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "chat_user",schema = "chat_app")
public class ChatUser {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "user_id", unique = true, nullable = false)
    @Type(type="pg-uuid")
    private UUID userId;

    @NotBlank
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Email
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password",nullable = false)
    private String password;

    @NotBlank
    @Column(name = "status",nullable = false)
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chat_user_friend", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<ChatUser> friends;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chat_user_friend", joinColumns = @JoinColumn(name = "friend_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<ChatUser> friendOf;


    //constructors
    public ChatUser(){

    }

    private ChatUser(String firstName, String lastName,String email,String password,String status){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    //getters,setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ChatUser> getFriends() {
        return friends;
    }

    public void setFriends(Set<ChatUser> friends) {
        this.friends = friends;
    }

    public Set<ChatUser> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Set<ChatUser> friendOf) {
        this.friendOf = friendOf;
    }

    //equals and hashcode
    @Override
    public boolean equals(Object object){
        if(!(object instanceof ChatUser)) return false;

        ChatUser temp = (ChatUser) object;
        return temp.getUserId().equals(getUserId()) &&
                temp.getEmail().equals(getEmail());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUserId(),getEmail());
    }

    //builder
    public static class Builder{
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String status;

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withPassword(String password){
            this.password = password;
            return this;
        }

        public Builder withStatus(String status){
            this.status = status;
            return this;
        }

        public Builder withDefaultStatus(){
            status = "Offline";
            return this;
        }

        public ChatUser build(){
            return new ChatUser(firstName, lastName,email,password,status);
        }
    }
}
