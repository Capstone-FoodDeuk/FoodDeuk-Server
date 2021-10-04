package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.HomeStoreDTO;
import CapstoneDesign.Server.domain.entity.store.Location;
import CapstoneDesign.Server.domain.entity.store.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    StoreRepository storeRepository;

    @Test
    public void 특정거리_이내의_활성화된_푸드트럭_리스트_가져오기() {

        // given
        Store store1 = new Store();
        Store store2 = new Store();
        Store store3 = new Store();

        store1.storeOpen(true);
        store2.storeOpen(false);
        store3.storeOpen(true);
        store1.updateStoreOpenInfo(new Location(37.62579, 127.07632), LocalDateTime.now());
        store2.updateStoreOpenInfo(new Location(37.62579, 127.07632), LocalDateTime.now());
        store3.updateStoreOpenInfo(new Location(36.06690, 129.38115), LocalDateTime.now());

        em.persist(store1);
        em.persist(store2);
        em.persist(store3);

        // when
        List<HomeStoreDTO> result = storeRepository.findActiveStoresByLocationLessThanDistance(37.62579, 127.07632, 2.0);

        // then
        assertThat(result).hasSize(1);
        assertThat(result).extracting("id").containsExactly(store1.getId());
    }
}