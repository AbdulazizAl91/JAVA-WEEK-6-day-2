package com.example.blogsystem.Service;

import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register(User user){
        String hash= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("USER");

        authRepository.save(user);
    }
    public void updateUser(Integer user_id,User user_info){
        User user = authRepository.findUserById(user_id);
        String hash= new BCryptPasswordEncoder().encode(user_info.getPassword());
        user.setUsername(user_info.getUsername());
        user.setPassword(hash);
        authRepository.save(user);

    }
    public void deleteUser(Integer user_id){
        User user = authRepository.findUserById(user_id);
        authRepository.delete(user);
    }
}
