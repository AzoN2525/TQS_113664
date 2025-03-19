package com.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> store = new ArrayList<>();
    
    public void Library(){
    }

    public void addBook(Book book){
        store.add(book);
    }

    public List<Book> findBooksByAuthor(String author){
        return store.stream().filter(b -> b.getAuthor().equals(author)).toList();
    }

    public List<Book> findBooks(LocalDateTime from, LocalDateTime to){
        return store.stream().filter(b -> b.getPublishedAt().isAfter(from) && b.getPublishedAt().isBefore(to)).toList();
    }

    public List<Book> getAllBooks(){
        return store;
    }
}