package com.sherbek.Library.System.book.service;


import com.sherbek.Library.System.book.model.Book;
import com.sherbek.Library.System.book.repository.BookRepository;
import com.sherbek.Library.System.user.model.User;
import com.sherbek.Library.System.user.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if (book != null && user != null) {
            if (book.getCurrentCount() <= book.getMaxCount()) {
                List<User> borrowedBy = book.getBorrowedBy();
                logger.info("Current borrowers of the book:");
                borrowedBy.forEach(it -> logger.info(it.toString()));

                borrowedBy.add(user); // Add user to the list of borrowers
                book.setCurrentCount(book.getCurrentCount() - 1); // Update current count of borrowed books
                book.setBorrowedBy(borrowedBy); // Update the borrowedBy list in Book entity

                logger.info("Updated book details: {}", book.toString());

                return bookRepository.save(book); // Save the updated book entity
            } else {
                // Handle case where book is already fully borrowed
                throw new RuntimeException("Book is already fully borrowed");
            }
        } else {
            // Handle cases where book or user is not found
            throw new RuntimeException("Book or User not found");
        }
    }

    public Book returnBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if (book != null && user != null) {
            if (book.getCurrentCount() <= book.getMaxCount()) {
                List<User> borrowedBy = book.getBorrowedBy();
                borrowedBy.remove(user); // Remove user to the list of borrowers
                book.setCurrentCount(book.getCurrentCount() + 1); // Update current count of borrowed books
                book.setBorrowedBy(borrowedBy); // Update the borrowedBy list in Book entity
                return bookRepository.save(book); // Save the updated book entity
            } else {
                // Handle case where book is already fully borrowed
                throw new RuntimeException("Book is already returned");
            }
        } else {
            // Handle cases where book or user is not found
            throw new RuntimeException("Book or User not found");
        }
    }
}