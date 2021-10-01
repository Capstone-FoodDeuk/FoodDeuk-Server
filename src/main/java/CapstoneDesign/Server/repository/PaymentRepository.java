package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.store.Payment;
import CapstoneDesign.Server.domain.entity.store.PaymentMethod;
import CapstoneDesign.Server.domain.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findPaymentByStoreAndMethod(Store store, PaymentMethod paymentMethod);
    List<Payment> findPaymentsByStore(Store store);
}
