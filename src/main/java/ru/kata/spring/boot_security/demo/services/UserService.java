package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь " + username + " не найден ");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User updatedFields) {
        User existingUser = userRepository.findById(updatedFields.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (updatedFields.getFirstName() != null) {
            existingUser.setFirstName(updatedFields.getFirstName());
        }
        if (updatedFields.getLastName() != null) {
            existingUser.setLastName(updatedFields.getLastName());
        }
        if (updatedFields.getAge() != null) {
            existingUser.setAge(updatedFields.getAge());
        }
        if (updatedFields.getEmail() != null) {
            existingUser.setEmail(updatedFields.getEmail());
        }
        if (!updatedFields.getRoles().isEmpty()) {
            existingUser.setRoles(updatedFields.getRoles());
        }

        if (updatedFields.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(updatedFields.getPassword()));
        }
        userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
