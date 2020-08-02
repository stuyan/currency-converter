package ru.stuyan.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stuyan.converter.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

}
