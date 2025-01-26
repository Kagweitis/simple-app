package com.simple.app.simpleapp.Controller;

import com.simple.app.simpleapp.Service.BooksService;
import com.simple.app.simpleapp.DTO.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BooksController {

    private BooksService booksService;

    public BooksController(BooksService booksService){
        this.booksService = booksService;
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Book getBooks(){
        return booksService.getBookBy();
    }

}
