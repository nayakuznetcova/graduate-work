package ru.skypro.homework.model.special;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Идентификатор для моделей
 */
@Data
@MappedSuperclass
public abstract class IdentifiedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
}
