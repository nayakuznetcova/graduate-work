package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.special.Role;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;

    /**
     * Если пользователя с таким логином не существует - ответ НЕТ
     * Если пользователь существует и введенный пароль совпадает - ответ ДА
     *
     * @param userName логин
     * @param password пароль
     * @return Результат
     */
    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    /**
     * Если пользователь с таким логином существует - ответ НЕТ
     * Иначе регистрируем пользователя
     *
     * @param register форма регистрации
     * @param role Роль
     * @return результат
     */
    @Override
    public boolean register(RegisterDto register, Role role) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        userService.createUser(register, role);
        return true;
    }
}
