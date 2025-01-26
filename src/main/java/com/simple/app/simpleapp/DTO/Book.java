package com.simple.app.simpleapp.DTO;

public record Book(
        Long id,
        String name,
        String author,
        double rating
) {
}
