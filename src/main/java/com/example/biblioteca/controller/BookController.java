package com.example.biblioteca.controller;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", service.listarTodos());
        return "lista";
    }

    @GetMapping("/titulo")
    public ResponseEntity<Book> buscaPorTitulo(@RequestParam String q) {
        Book livro = service.buscarPorTitulo(q);
        if (livro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(livro);
        }
        @GetMapping("/{id}")
    public ResponseEntity<Book> buscarPorId(@PathVariable Long id) {
        Book livro = service.buscarPorId(id);
        if (livro == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(livro);
    }
    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Book livro) {
        livro.setId(id);
        service.salvar(livro);
        return "redirect: /livros";
    }

    @PostMapping("/{id}/excluir")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
