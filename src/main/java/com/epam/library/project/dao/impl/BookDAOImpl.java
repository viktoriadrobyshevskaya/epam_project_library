package com.epam.library.project.dao.impl;

import com.epam.library.project.dao.BookDAO;
import com.epam.library.project.dao.connection.ConnectionPool;
import com.epam.library.project.dao.connection.ConnectionPoolException;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Author;
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
            preparedStatement = connection.prepareStatement(SQLQuery.All_BOOKS);

            resultSet = preparedStatement.executeQuery();

            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                int id_author = resultSet.getInt(3);
                String year = resultSet.getString(4);
                int numberOfCopies = resultSet.getInt(5);

                Book book = new Book(id, title, id_author, year, numberOfCopies);
                book.setAuthor(DAOFactory.getInstance().getAuthorDAO().getAuthorById(id_author));
                books.add(book);
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
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ADD_BOOK)) {
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
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_BOOK)) {
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
            preparedStatement = connection.prepareStatement(SQLQuery.UPDATE_BOOK);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getId_author());
            preparedStatement.setString(3, book.getYearOfPublication());
            preparedStatement.setInt(4, book.getNumberOfCopies());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

    @Override
    public void updateNumberOfCopies(int bookId, int number) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.UPDATE_NUMBER_OF_COPIES);
            preparedStatement.setInt(1, number);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

    @Override
    public Book findBookById(int bookId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.FIND_BOOK);
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();

            Book book = null;

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                int id_author = resultSet.getInt(3);
                String year = resultSet.getString(4);
                int numberOfCopies = resultSet.getInt(5);

                book = new Book(id, title, id_author, year, numberOfCopies);
                book.setAuthor(DAOFactory.getInstance().getAuthorDAO().getAuthorById(id_author));
            }

            return book;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Book> getBooksBySearch(String search) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.FIND_BOOK_BY_SEARCH)) {

            statement.setString(1, "%" + search + "%");
            ResultSet resultSet = statement.executeQuery();

            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                int id_author = resultSet.getInt(3);
                String year = resultSet.getString(4);
                int numberOfCopies = resultSet.getInt(5);

                Book book = new Book(id,title,id_author,year,numberOfCopies);

                Author author = new Author();
                author.setId_author(resultSet.getInt(6));
                author.setName(resultSet.getString(7));
                author.setMiddleName(resultSet.getString(8));
                author.setSurname(resultSet.getString(9));
                author.setYearOfBirth(resultSet.getInt(10));

                book.setAuthor(author);
                books.add(book);
            }
            resultSet.close();

            return books;

        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

}
