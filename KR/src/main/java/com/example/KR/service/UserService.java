package com.example.KR.service;
import com.example.KR.models.User;
import com.example.KR.models.enums.Role;
import com.example.KR.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRep;
    private final PasswordEncoder passwordEncoder;

    public boolean createUsers(User user) {
        if (userRep.findByUsername(user.getUsername()).isPresent() ||
                userRep.findByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setActive(true);
        user.getRole().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRep.save(user);
        log.info("Пользователь сохранен: {}", user.getUsername());
        return true;
    }
    public User findByUsername(String username) {
        return userRep.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}