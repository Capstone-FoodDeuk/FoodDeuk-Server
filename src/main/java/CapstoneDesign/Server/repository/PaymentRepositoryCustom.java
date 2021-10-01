package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.store.PaymentMethod;

import java.util.List;

public interface PaymentRepositoryCustom {

    List<PaymentMethod> findPaymentMethodsByStoreId(Long id);
}