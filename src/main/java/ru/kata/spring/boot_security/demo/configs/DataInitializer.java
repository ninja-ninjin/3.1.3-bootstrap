package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = roleService.createRoleIfNotExists("ROLE_ADMIN");
        Role roleUser = roleService.createRoleIfNotExists("ROLE_USER");

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setAge(35);
        admin.setEmail("admin@mail.ru");  //username
        admin.setPassword("admin");
        admin.getRoles().add(roleAdmin);
        admin.getRoles().add(roleUser);
        userService.saveUser(admin);

        User user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setAge(25);
        user.setEmail("user@mail.ru");  //username
        user.setPassword("user");
        user.getRoles().add(roleUser);
        userService.saveUser(user);
    }
}
