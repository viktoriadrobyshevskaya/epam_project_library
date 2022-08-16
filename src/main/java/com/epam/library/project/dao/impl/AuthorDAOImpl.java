package com.epam.library.project.dao.impl;

import com.epam.library.project.dao.AuthorDAO;
import com.epam.library.project.dao.connection.ConnectionPool;
import com.epam.library.project.dao.connection.ConnectionPoolException;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public void addAuthor(Author author) throws DAOException {
        String sql = "INSERT INTO authors(name, middleName, surName, yearOfBirth) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getMiddleName());
            ps.setString(3, author.getSurname());
            ps.setInt(4, author.getYearOfBirth());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void removeAuthor(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM authors WHERE id_author = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Author getById(int id) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors WHERE id_author = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                Author author = new Author();
                author.setId_author(id);
                author.setName(resultSet.getString(2));
                author.setMiddleName(resultSet.getString(3));
                author.setSurname(resultSet.getString(4));
                author.setYearOfBirth(resultSet.getInt(5));
                return author;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Author> getAllAuthors() throws DAOException {
        List<Author> allAuthors = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Author author = new Author();
                    author.setId_author(resultSet.getInt(1));
                    author.setName(resultSet.getString(2));
                    author.setMiddleName(resultSet.getString(3));
                    author.setSurname(resultSet.getString(4));
                    author.setYearOfBirth(resultSet.getInt(5));
                    allAuthors.add(author);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return allAuthors;
    }
}
