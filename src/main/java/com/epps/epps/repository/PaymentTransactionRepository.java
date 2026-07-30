package com.epps.epps.repository;

import com.epps.epps.model.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, UUID> { }
