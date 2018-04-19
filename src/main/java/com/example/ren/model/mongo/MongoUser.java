package com.example.ren.model.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户，对接mongo
 *
 * @author qiang.ren
 * @date 2018/4/16
 */
@Document(collection = "user")
public class MongoUser {

    @Id
    private String userId;

    @NotNull @Indexed(unique = true)
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String realName;

    @NotNull
    private String email;

    @NotNull @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private Date registrationDate = new Date();

    private Set<String> roles = new HashSet<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public MongoUser() {
    }

    @PersistenceConstructor
    public MongoUser(String userId, @NotNull String userName, @NotNull String password, @NotNull String realName,
                     @NotNull String email, @NotNull Date registrationDate, Set<String> roles) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.roles = roles;
    }
}
