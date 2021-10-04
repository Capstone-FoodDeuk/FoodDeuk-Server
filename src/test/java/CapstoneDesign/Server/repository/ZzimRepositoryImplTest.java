package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.UserPageZzimDTO;
import CapstoneDesign.Server.domain.entity.Zzim;
import CapstoneDesign.Server.domain.entity.store.Category;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ZzimRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    ZzimRepository zzimRepository;

    @Test
    public void 특정유저의_찜목록_조회() {

        // given
        Store store1 = new Store();
        Store store2 = new Store();
        store1.updateStoreInfo("스토어1", Category.Fruit, "1", "store1");
        store2.updateStoreInfo("스토어2", Category.Snack, "2", "store2");

        GuestUser user = new GuestUser("user", "password", "유저1", "010-1234-5678");

        Zzim zzim1 = new Zzim(user, store1);
        Zzim zzim2 = new Zzim(user, store2);

        em.persist(store1);
        em.persist(store2);
        em.persist(user);

        // when
        List<UserPageZzimDTO> result = zzimRepository.findZzimsByUserId(user.getId());

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting("name").containsExactly("스토어1", "스토어2");
    }
}