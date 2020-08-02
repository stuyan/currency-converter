package ru.stuyan.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stuyan.converter.entity.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    @Query(value = "select ratio from exchange where currency = :currency " +
                   "and date = current_date",
            nativeQuery = true)
    Float findRatioByCurrency(@Param("currency") String currency);

}
