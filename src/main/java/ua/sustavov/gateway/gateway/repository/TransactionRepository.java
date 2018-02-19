package ua.sustavov.gateway.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sustavov.gateway.gateway.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
