package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body("user registered");
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @RequestBody @Valid User user_info){
        authService.updateUser(user.getId(),user_info);
        return ResponseEntity.status(200).body("user updated");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user){
        authService.deleteUser(user.getId());
        return ResponseEntity.status(200).body("user deleted");

    }





}
