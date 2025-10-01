package com.example.biblioteca.controller;

import com.example.biblioteca.model.Book;
import com.example.biblioteca.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // LISTA + BUSCA
    @GetMapping("/livros")
    public String listar(@RequestParam(value = "q", required = false) String q, Model model) {
        if (q == null || q.isBlank()) {
            model.addAttribute("livros", service.listarTodos());
        } else {
            List<Book> livros = service.buscarPorTitulo(q);
            model.addAttribute("busca", q);
            if (livros.isEmpty()) {
                model.addAttribute("livros", List.of());
                model.addAttribute("erro", "Nenhum livro encontrado para: " + q);
            } else {
                model.addAttribute("livros", livros);
            }
        }
        model.addAttribute("livro", new Book());
        return "lista";
    }

    @PostMapping({"/livros", "/livros/"})
    public String criar(@Valid @ModelAttribute("livro") Book livro, BindingResult result, RedirectAttributes ra, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("livros", service.listarTodos());
            model.addAttribute("erro", result.getAllErrors().get(0).getDefaultMessage());
            return "lista";
        }
        try {
            service.salvar(livro);
            ra.addFlashAttribute("sucesso", "Livro adicionado: " + livro.getTitulo());
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/livros";
    }

    @PostMapping({"/livros/{id}", "/livros/{id}/"})
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute Book livro, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("erro", result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/livros";
        }
        livro.setId(id);
        service.salvar(livro);
        ra.addFlashAttribute("sucesso", "Livro atualizado: " + livro.getTitulo());
        return "redirect:/livros";
    }

    @PostMapping({"/livros/{id}/excluir", "/livros/{id}/excluir/"})
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        service.remover(id);
        ra.addFlashAttribute("sucesso", "Livro excluído com sucesso.");
        return "redirect:/livros";
    }
}