package com.sherbek.Library.System.book.repository;

import com.sherbek.Library.System.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}