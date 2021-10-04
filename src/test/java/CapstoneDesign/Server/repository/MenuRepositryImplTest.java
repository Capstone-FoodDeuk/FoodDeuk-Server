package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.StoreDetailMenuDTO;
import CapstoneDesign.Server.domain.entity.store.Menu;
import CapstoneDesign.Server.domain.entity.store.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
@SpringBootTest
@Transactional
class MenuRepositryImplTest {


    @Autowired
    EntityManager em;

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void 푸드트럭ID값으로_메뉴리스트_가져오기() {

        // given
        Store store = new Store();
        Menu menu1 = new Menu(store, "메뉴1", 1000L, false);
        Menu menu2 = new Menu(store, "메뉴2", 1500L, false);
        Menu menu3 = new Menu(store, "메뉴3", 2000L, true);
        em.persist(store);

        // when
        List<StoreDetailMenuDTO> result = menuRepository.findMenuListByStoreId(store.getId());

        // then
        assertThat(result).hasSize(3);
        assertThat(result).extracting("name").containsExactly("메뉴1", "메뉴2", "메뉴3");
    }
}