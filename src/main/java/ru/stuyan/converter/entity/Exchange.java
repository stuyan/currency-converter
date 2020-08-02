package ru.stuyan.converter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Exchange {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "currency")
    private Currency currency;

    private float ratio;

    @Temporal(TemporalType.DATE)
    private Date date;
}
