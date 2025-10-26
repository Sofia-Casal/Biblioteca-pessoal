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
//@GetMapping("/livro")
@SessionAttributes()


public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // LISTA + BUSCA
    @GetMapping("/livros")
    public String listar(@RequestParam(value = "q", required = false) String q, org.springframework.ui.Model model, jakarta.servlet.http.HttpSession session) {

        if (session.getAttribute("usuarioEmail") == null) {
            return "redirect:/login";
        }
        if (q != null) {
            session.setAttribute("filtro", q);
        } else {
            q = (String) session.getAttribute("filtro");
        }
        java.util.List<com.example.biblioteca.model.Book> livros;
        if (q == null || q.isBlank()) {
            livros = service.listarTodos();
        } else {
            livros = service.buscarPorTitulo(q);
            model.addAttribute("busca", q);
            if (livros.isEmpty()) {
                model.addAttribute("erro", "Nenhum livro encontrado para: " + q);
            }
        }
        model.addAttribute("livros", livros);
        model.addAttribute("livro", new com.example.biblioteca.model.Book());
        return "lista";
    }

    @PostMapping({"/livros", "/livros/"})
    public String criar(@Valid @ModelAttribute("livro") Book livro, BindingResult result, RedirectAttributes ra, Model model) {

        if (livro.getDataInicio() != null && livro.getDataFim() != null
                && livro.getDataInicio().isAfter(livro.getDataFim())) {
            result.reject("periodoInvalido", "A data de início não pode ser depois da data de fim.");
        }
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

        if (livro.getDataInicio() != null && livro.getDataFim() != null
                && livro.getDataInicio().isAfter(livro.getDataFim())) {
            result.reject("periodoInvalido", "A data de início não pode ser depois da data de fim.");
        }
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

    @GetMapping("/livros/limpar")
    public String limparFiltro(jakarta.servlet.http.HttpSession session) {
        session.removeAttribute("filtro");
        return "redirect:/livros";
    }

}