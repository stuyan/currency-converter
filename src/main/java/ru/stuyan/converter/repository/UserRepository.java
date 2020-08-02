package ru.stuyan.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stuyan.converter.entity.SecurityUser;

public interface UserRepository extends JpaRepository<SecurityUser, Long> {

    SecurityUser findByLogin(String login);

    boolean existsByLogin(String login);

}
