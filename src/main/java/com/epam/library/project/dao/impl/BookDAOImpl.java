package com.epam.library.project.dao.impl;

import com.epam.library.project.dao.BookDAO;
import com.epam.library.project.dao.connection.ConnectionPool;
import com.epam.library.project.dao.connection.ConnectionPoolException;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> showAllBooks() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(BookQuery.All_USERS);

            resultSet = preparedStatement.executeQuery();

            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                int id_author = resultSet.getInt(3);
                String year = resultSet.getString(4);
                int numberOfCopies = resultSet.getInt(5);

                books.add(new Book(title, id_author, year, numberOfCopies));
            }

            return books;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void addBook(Book book) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(BookQuery.ADD_BOOK)) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getId_author());
            statement.setString(3, book.getYearOfPublication());
            statement.setInt(4, book.getNumberOfCopies());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public void deleteBookById(int id) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(BookQuery.DELETE_BOOK)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Something wrong!" + e);
        }
    }

    @Override
    public void updateBookById(int id, Book book) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(BookQuery.UPDATE_BOOK);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getId_author());
            preparedStatement.setString(4, book.getYearOfPublication());
            preparedStatement.setInt(5, book.getNumberOfCopies());

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

}
