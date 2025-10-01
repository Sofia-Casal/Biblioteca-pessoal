package com.example.biblioteca.repository;

import com.example.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book>
    findByTituloAndAutor(String titulo, String autor);

    List<Book>
    findByTituloContainingIgnoreCase(String titulo);
}
