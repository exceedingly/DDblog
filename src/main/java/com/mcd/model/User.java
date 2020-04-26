package com.mcd.model;

import lombok.Data;

/**
 * Created by MaoChenDong on 2020/3/23.
 */

public class User {
    private String username;
    private String password;
    private String token;
    private String avatar_url;
    private String account_id;
    private long GMT_CREATE;
    private long GMT_MODIFIED;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public long getGMT_CREATE() {
        return GMT_CREATE;
    }

    public void setGMT_CREATE(long GMT_CREATE) {
        this.GMT_CREATE = GMT_CREATE;
    }

    public long getGMT_MODIFIED() {
        return GMT_MODIFIED;
    }

    public void setGMT_MODIFIED(long GMT_MODIFIED) {
        this.GMT_MODIFIED = GMT_MODIFIED;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", account_id='" + account_id + '\'' +
                ", GMT_CREATE=" + GMT_CREATE +
                ", GMT_MODIFIED=" + GMT_MODIFIED +
                '}';
    }
}
