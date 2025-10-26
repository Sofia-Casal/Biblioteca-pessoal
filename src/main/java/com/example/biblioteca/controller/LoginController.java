package com.example.biblioteca.controller;

import com.example.biblioteca.model.User;
import com.example.biblioteca.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;
    public LoginController(UserService userService) { this.userService = userService; }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        HttpSession session) {

        Optional<User> auth = userService.autenticar(email, senha);
        if (auth.isPresent()) {
            session.setAttribute("usuarioEmail", auth.get().getEmail());
            session.setAttribute("usuarioNome", auth.get().getNome());
            return "redirect:/livros";
        } else {
            session.setAttribute("erroLogin", "E-mail ou senha incorretos!");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // (Opcional) Redirecionar raiz para login
    @GetMapping("/")
    public String raiz(HttpSession session) {
        return (session.getAttribute("usuarioEmail") == null) ? "redirect:/login" : "redirect:/livros";
    }
}
