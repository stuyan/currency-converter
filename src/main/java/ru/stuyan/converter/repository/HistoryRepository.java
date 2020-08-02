package ru.stuyan.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stuyan.converter.entity.History;
import ru.stuyan.converter.entity.SecurityUser;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "select * from history where from_currency = :from " +
                   "and to_currency = :to and date_trunc('day', date) = :date " +
                   "and user_id = :user",
            nativeQuery = true)
    List<History> findByCurrencyAndDate(@Param("from") String fromCurrency,
                                        @Param("to") String toCurrency,
                                        @Param("date") LocalDateTime date,
                                        @Param("user") Long user);

    List<History> findByUser(SecurityUser user);

}
