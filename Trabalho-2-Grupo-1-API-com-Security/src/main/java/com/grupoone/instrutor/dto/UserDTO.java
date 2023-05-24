package com.grupoone.instrutor.dto;

public class UserDTO {

    private Integer id;
    private String username;
    private String email;

    public UserDTO() {
        super();
    }

    public UserDTO(Integer id, String username, String email) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
