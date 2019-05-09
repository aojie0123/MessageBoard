package com.imooc.domain;

public class User {

    private Integer uid;
    private String username;
    private String password;
    private String real_name;
    private String birthday;
    private String tel;
    private String address;

    public User() {
    }

    public User(Integer uid, String username, String password, String real_name, String birthday, String tel, String address) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.real_name = real_name;
        this.birthday = birthday;
        this.tel = tel;
        this.address = address;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", real_name='" + real_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
