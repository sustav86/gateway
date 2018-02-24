package ua.sustavov.gateway.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;

public interface AuthTransactionRepository extends JpaRepository<AuthTransaction, Long> {
}
