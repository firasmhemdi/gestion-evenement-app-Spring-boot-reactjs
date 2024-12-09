package com.example.projetd.integration.Controller;

import com.example.projetd.integration.Entity.User;
import com.example.projetd.integration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/inscription")
    public User inscrireUtilisateur(@RequestBody User utilisateur) {
        return userService.inscrireUtilisateur(utilisateur);
    }

    @PostMapping("/{userId}/participer/{eventId}")
    public String participerEvenement(@PathVariable Long userId, @PathVariable Long eventId) {
        return "L'utilisateur avec ID " + userId + " a participé à l'événement " + eventId;
    }
}
