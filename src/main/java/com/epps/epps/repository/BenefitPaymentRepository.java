package com.epps.epps.repository;

import com.epps.epps.model.BenefitPaymentTransaction;
import com.epps.epps.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitPaymentRepository
        extends JpaRepository<BenefitPaymentTransaction, String> {

    List<BenefitPaymentTransaction> findByEmployeeId(String employeeId);

    List<BenefitPaymentTransaction> findByBenefitId(String benefitId);

    List<BenefitPaymentTransaction> findByStatus(PaymentStatus status);

    boolean existsByBenefitIdAndStatus(String benefitId,
                                       PaymentStatus status);

}
