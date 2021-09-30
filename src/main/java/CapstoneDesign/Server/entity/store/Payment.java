package CapstoneDesign.Server.entity.store;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    /**
     * use to add payment method
     * A 푸드트럭이 결제방식(B)을 추가할 때 => Payment(A, B)
     */
    public Payment(Store store, PaymentMethod method) {
        this.store = store;
        this.method = method;
        store.getPayments().add(this);
    }
}