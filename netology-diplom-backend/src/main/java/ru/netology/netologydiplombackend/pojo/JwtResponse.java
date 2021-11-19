package ru.netology.netologydiplombackend.pojo;

import lombok.Data;


@Data
public class JwtResponse {

    private String auth_token;
    private String type = "Bearer";
    private Long id;
    private String login;

    public JwtResponse(String auth_token, Long id, String login) {
        this.auth_token = auth_token;
        this.id = id;
        this.login = login;
    }
}
