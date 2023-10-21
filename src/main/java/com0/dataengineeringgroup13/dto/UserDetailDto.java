package com0.dataengineeringgroup13.dto;

import java.io.Serializable;

/**
 * user dto
 *
 * @author Tung Vo
 * @since 21/October/2023
 */
public class UserDetailDto implements Serializable {

    private Integer userId;

    private String userName;

    private String lastName;

    private String firstName;

    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
