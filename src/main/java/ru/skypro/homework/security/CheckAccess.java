package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.model.special.Role;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.security.Principal;
@RequiredArgsConstructor
@Component
public class CheckAccess {
    private final UserRepository userRepository;
    private final AdRepository adRepository;

    public boolean isAdminOrOwnerAd(int id, Principal principal) {
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUsername(name);
        AdEntity adEntity = adRepository.findById(id);
        return userEntity.getRole().equals(Role.ADMIN)
                || adEntity.getUser().getId().equals(userEntity.getId());
    }
}
