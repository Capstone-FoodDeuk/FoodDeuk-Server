package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.QPayment;
import CapstoneDesign.Server.domain.entity.store.Payment;
import CapstoneDesign.Server.domain.entity.store.PaymentMethod;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static CapstoneDesign.Server.domain.entity.QPayment.*;

@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PaymentMethod> findPaymentMethodsByStoreId(Long id) {

        List<Payment> payments = queryFactory
                .selectFrom(payment)
                .where(payment.store.id.eq(id))
                .fetch();
        List<PaymentMethod> paymentMethods = payments.stream()
                .map(payment -> payment.getMethod())
                .collect(Collectors.toList());

        return paymentMethods;
    }
}
