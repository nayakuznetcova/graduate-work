package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserEntity;

import java.util.Optional;

/**
 * Репозиторий пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * Найти пользователя по логину
     * @param username логин
     * @return результат поиска
     */
    UserEntity findByUsername (String username);

    /**
     * Найти пользователя по логину
     * @param username логин
     * @return результат поиска в обертке
     */
    Optional<UserEntity> findUserEntityByUsername(String username);
}
