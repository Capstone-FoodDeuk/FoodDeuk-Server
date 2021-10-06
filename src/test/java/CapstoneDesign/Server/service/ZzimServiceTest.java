package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.entity.Zzim;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.repository.ZzimRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ZzimServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ZzimService zzimService;

    @Autowired
    ZzimRepository zzimRepository;

    @Test
    public void 푸드트럭_찜여부_확인() {

        // given
        GuestUser guest = new GuestUser("id", "pwd", "유저", "010-1234-5678");
        Store store = new Store();
        Zzim zzim = new Zzim(guest, store);
        em.persist(guest);
        em.persist(store);

        // when
        boolean result = zzimService.checkZzimStore(guest, store);

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void 푸드트럭_찜하기() {

        // given
        GuestUser guest = new GuestUser("id", "pwd", "유저", "010-1234-5678");
        Store store = new Store();
        em.persist(guest);
        em.persist(store);

        // when
        zzimService.zzimStore(guest, store);

        // then
        assertThat(guest.getZzimList()).hasSize(1);
    }

    @Test
    public void 푸드트럭_찜_취소하기() {

        // given
        GuestUser guest = new GuestUser("id", "pwd", "유저", "010-1234-5678");
        Store store = new Store();
        Zzim zzim = new Zzim(guest, store);
        em.persist(guest);
        em.persist(store);

        // when
        zzimService.zzimStore(guest, store);

        // then
        assertThat(guest.getZzimList()).hasSize(0);
    }
}