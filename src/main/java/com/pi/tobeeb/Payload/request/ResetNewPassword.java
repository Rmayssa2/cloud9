package com.pi.tobeeb.Payload.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class ResetNewPassword {
    private String email;
    private String code;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
