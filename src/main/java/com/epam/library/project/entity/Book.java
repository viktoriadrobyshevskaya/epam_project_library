package com.epam.library.project.entity;

import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private int id_author;
    private String yearOfPublication;
    private int numberOfCopies;

    public Book() {
    }

    public Book(String title, int id_author, String yearOfPublication, int numberOfCopies) {
        this.title = title;
        this.id_author = id_author;
        this.yearOfPublication = yearOfPublication;
        this.numberOfCopies = numberOfCopies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && id_author == book.id_author && numberOfCopies == book.numberOfCopies && Objects.equals(title, book.title) && Objects.equals(yearOfPublication, book.yearOfPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, id_author, yearOfPublication, numberOfCopies);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", id_author=" + id_author +
                ", yearOfPublication='" + yearOfPublication + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }
}
