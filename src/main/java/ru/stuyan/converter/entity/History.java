package ru.stuyan.converter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class History {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "from_currency")
    private Currency fromCurrency;

    @ManyToOne
    @JoinColumn(name = "to_currency")
    private Currency toCurrency;

    private int fromSum;

    private float toSum;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SecurityUser user;

}
