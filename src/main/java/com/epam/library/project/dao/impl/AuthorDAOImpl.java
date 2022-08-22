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
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(AuthorQuery.ADD_AUTHOR)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getMiddleName());
            ps.setString(3, author.getSurname());
            ps.setInt(4, author.getYearOfBirth());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public void removeAuthor(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(AuthorQuery.DELETE_AUTHOR)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public void updateAuthor(int id, Author author) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AuthorQuery.UPDATE_AUTHOR);

            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getMiddleName());
            preparedStatement.setString(3, author.getSurname());
            preparedStatement.setInt(4, author.getYearOfBirth());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

    @Override
    public Author getById(int id) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(AuthorQuery.FIND_AUTHOR)) {

            statement.setInt(1, id);

            Author author = new Author();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                author.setId_author(id);
                author.setName(resultSet.getString(2));
                author.setMiddleName(resultSet.getString(3));
                author.setSurname(resultSet.getString(4));
                author.setYearOfBirth(resultSet.getInt(5));
            }

            resultSet.close();

            return author;

        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public List<Author> getAllAuthors() throws DAOException {
        List<Author> allAuthors = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(AuthorQuery.All_AUTHORS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Author author = new Author();
                author.setId_author(resultSet.getInt(1));
                author.setName(resultSet.getString(2));
                author.setMiddleName(resultSet.getString(3));
                author.setSurname(resultSet.getString(4));
                author.setYearOfBirth(resultSet.getInt(5));
                allAuthors.add(author);
            }

            resultSet.close();

            return allAuthors;

        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }
}
