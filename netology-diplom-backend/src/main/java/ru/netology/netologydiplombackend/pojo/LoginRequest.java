package ru.netology.netologydiplombackend.pojo;


import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;

}
