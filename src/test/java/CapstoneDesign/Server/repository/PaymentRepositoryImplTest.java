package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.entity.store.Payment;
import CapstoneDesign.Server.domain.entity.store.PaymentMethod;
import CapstoneDesign.Server.domain.entity.store.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PaymentRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    public void 푸드트럭ID값으로_결제수단목록_가져오기() {

        // given
        Store store = new Store();
        Payment p1 = new Payment(store, PaymentMethod.Cash);
        Payment p2 = new Payment(store, PaymentMethod.Card);
        em.persist(store);

        // when
        List<PaymentMethod> result = paymentRepository.findPaymentMethodsByStoreId(store.getId());

        // then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(PaymentMethod.Cash, PaymentMethod.Card);
    }
}