package CapstoneDesign.Server.entity.store;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {
    Cash("현금"),
    Card("카드"),
    BankTransfer("계좌이체");

    private final String paymentMethod;
}