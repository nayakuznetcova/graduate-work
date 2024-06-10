package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий объявлений
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    List<AdEntity> findAllByUser(UserEntity userEntity);
    AdEntity findById (int id);
}
