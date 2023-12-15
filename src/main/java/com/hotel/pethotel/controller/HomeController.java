package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ClientService clientService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("userRoles", SecurityContextHolder
                .getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("isAuthenticated", SecurityContextHolder
                .getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return "Homecontroller/home";
    }

    @GetMapping("/login")
    public String login() {
        return "Homecontroller/login";
    }

    @GetMapping("/adminpanel")
    public String adminPanel() {
        return "Admin/adminpanel";
    }


    @GetMapping("/clientpanel")
    public String userPanel() {
        return "Client/clientpanel";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/register")//czy clientAdd? tutaj czy w homeController?
    public String getAddClient(Model model) {
        model.addAttribute("newClient", new ClientModel());
        return "Homecontroller/addClient";
    }


    @PostMapping("/register")
    public RedirectView postAddClient(ClientModel client) {
        clientService.addClient(client);
        return new RedirectView("/");
    }
}