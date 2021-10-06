package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.dto.MenuCreateDTO;
import CapstoneDesign.Server.domain.dto.MenuUpdateDTO;
import CapstoneDesign.Server.domain.entity.store.Category;
import CapstoneDesign.Server.domain.entity.store.PaymentMethod;
import CapstoneDesign.Server.domain.entity.store.Store;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    StoreService storeService;

    @Test
    public void 푸드트럭_정보설정_메뉴추가만_하는_경우() throws IOException {

        // given
        Store store = new Store();

        List<MenuCreateDTO> createMenus = new ArrayList<MenuCreateDTO>();
        MenuCreateDTO newMenu = new MenuCreateDTO();
        newMenu.setName("타코야끼");
        newMenu.setPrice(3000L);
        newMenu.setSoldOut(false);
        MockMultipartFile image = new MockMultipartFile("image",
                "test.png",
                "image/png",
                new FileInputStream("/Users/ajyng/Desktop/logo.png"));
        newMenu.setImage(image);
        createMenus.add(newMenu);
        List<MenuUpdateDTO> updateMenus = new ArrayList<MenuUpdateDTO>();
        List<MenuUpdateDTO> deleteMenus = new ArrayList<MenuUpdateDTO>();

        List<PaymentMethod> payments = new ArrayList<PaymentMethod>();
        payments.add(PaymentMethod.Card);
        payments.add(PaymentMethod.Cash);
        em.persist(store);

        // when
        storeService.updateStoreInfo(store, "맛난 타코야끼", Category.Takoyaki, "123-567", "컴온",
                createMenus, updateMenus, deleteMenus, payments);

        // then
        assertThat(store.getName()).isEqualTo("맛난 타코야끼");
        assertThat(store.getCategory()).isEqualTo(Category.Takoyaki);
        assertThat(store.getMenuList()).hasSize(1);
        assertThat(store.getPayments()).hasSize(2);
    }
}