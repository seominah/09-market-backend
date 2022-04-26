package com.gonggu.market.api.dto.auth;

import com.gonggu.market.api.domain.address.Address;

public class SignupDto {
    private String email;
    private String password;
    private String nickname;
    private String mobile;
    private String address;
    private String zipcode;

    public SignupDto() {
    }

    public SignupDto(String email, String password, String nickname, String mobile, String address, String zipcode) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.address = address;
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
