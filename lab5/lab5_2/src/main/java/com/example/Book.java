package com.example;

import java.time.LocalDateTime;

public class Book {
    
    private String author;
    private String title;
    private LocalDateTime publishedAt;

    public Book(String author, String title, LocalDateTime publishedAt) {
        this.author = author;
        this.title = title;
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", publishedAt=" + publishedAt + ", title=" + title + "]";
    }
}
