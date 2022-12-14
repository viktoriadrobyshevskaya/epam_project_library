package com.epam.library.project.entity;

import java.util.Objects;

public class Author {
    private int id_author;
    private String name;
    private String middleName;
    private String surname;
    private int yearOfBirth;

    public Author() {
    }

    public Author(String name, String middleName, String surname, int yearOfBirth) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
    }

    public Author(int id_author, String name, String middleName, String surname, int yearOfBirth) {
        this.id_author = id_author;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id_author == author.id_author && yearOfBirth == author.yearOfBirth && Objects.equals(name, author.name) && Objects.equals(middleName, author.middleName) && Objects.equals(surname, author.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_author, name, middleName, surname, yearOfBirth);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id_author=" + id_author +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
