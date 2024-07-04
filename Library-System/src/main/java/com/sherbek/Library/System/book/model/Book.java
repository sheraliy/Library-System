package com.sherbek.Library.System.book.model;

import com.sherbek.Library.System.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int maxCount;
    private int currentCount;

    @ManyToMany(mappedBy = "borrowedBooks")
    private List<User> borrowedBy;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", maxCount=" + maxCount +
                ", currentCount=" + currentCount +
                ", borrowedBy=" + borrowedBy +
                '}';
    }
}