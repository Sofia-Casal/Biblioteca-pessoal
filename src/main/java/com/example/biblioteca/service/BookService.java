package com.example.biblioteca.service;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    public BookService(BookRepository repository) {
        this.repository = repository;
    }
    public List<Book> listarTodos() {
        return repository.findAll();
    }
    public Book buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
    public Book buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }
    public Book salvar(Book livro) {
        return repository.save(livro);
    }
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
