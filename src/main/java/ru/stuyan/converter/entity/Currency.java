package ru.stuyan.converter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Currency {

    @Id
    private String id;

    private String name;

}
