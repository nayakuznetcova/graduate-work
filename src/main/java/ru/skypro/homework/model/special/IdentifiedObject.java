package ru.skypro.homework.model.special;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IdentifiedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
