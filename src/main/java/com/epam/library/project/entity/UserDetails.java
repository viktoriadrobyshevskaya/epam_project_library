package com.epam.library.project.entity;

import java.util.Objects;

public class UserDetails {

    private int id;
    private int userId;
    private String name;
    private String surname;
    private String phone;
    private String address;

    private User user;

    public UserDetails(int userId, String name, String surname, String phone, String address) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public UserDetails(int id, int userId, String name, String surname, String phone, String address) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return id == that.id && userId == that.userId && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, surname, phone, address);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }
}
