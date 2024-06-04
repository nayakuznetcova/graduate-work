package ru.skypro.homework.model.special;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class IdentifiedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
}
