package com.simple.app.simpleapp.Service;

import com.simple.app.simpleapp.DTO.Book;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    public Book getBookBy() {
        return new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", 4.5);
    }
}
