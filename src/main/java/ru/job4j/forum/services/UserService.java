package ru.job4j.forum.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Role;
import ru.job4j.forum.models.User;
import ru.job4j.forum.repository.RoleRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<Role> getRole(String title) {
        return roleRepository.findByTitle(title);
    }

    public Role getRoleUser() {
        String title = "USER";
        var role = getRole(title);
        log.debug("Role USER: {}", getRole(title).get());
        if (role.isEmpty()) {
            role = Optional.of(roleRepository.save(Role.of(title)));
        }
        return role.get();
    }

    public boolean registration(User user) {
        if (getUser(user.getUsername()).isEmpty()) {
            user.setRole(getRoleUser());
            user.setPassword(encoder.encode(user.getPassword()));
            user.setActive(true);
            user.setId(0);
            log.debug("User: {}", user);
            addUser(user);
            return true;
        } else {
            log.error("User is have in database");
            return false;
        }
    }
}
