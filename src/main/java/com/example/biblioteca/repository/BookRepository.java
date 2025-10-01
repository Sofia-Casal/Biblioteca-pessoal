package com.example.biblioteca.repository;

import com.example.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTituloContainingIgnoreCase(String titulo);
}
