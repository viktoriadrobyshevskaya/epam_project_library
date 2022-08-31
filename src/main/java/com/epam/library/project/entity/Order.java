package com.epam.library.project.entity;

import java.util.Objects;

public class Order {
    private int id;
    private int userId;
    private int bookId;
    private String status;

    private User user;
    private Book book;

    public Order(int id, int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Order(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Order(int userId, int bookId, String status) {
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
    }

    public Order(int id, int userId, int bookId, String status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userId == order.userId && bookId == order.bookId && Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookId, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", status='" + status + '\'' +
                '}';
    }
}
