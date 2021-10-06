package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.dto.MenuCreateDTO;
import CapstoneDesign.Server.domain.dto.MenuDeleteDTO;
import CapstoneDesign.Server.domain.dto.MenuUpdateDTO;
import CapstoneDesign.Server.domain.entity.store.*;
import CapstoneDesign.Server.exception.DuplicatedMenuException;
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

        List<MenuCreateDTO> createMenus = new ArrayList<>();
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
        List<MenuUpdateDTO> updateMenus = new ArrayList<>();
        List<MenuDeleteDTO> deleteMenus = new ArrayList<>();

        List<PaymentMethod> payments = new ArrayList<>();
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

    @Test
    public void 푸드트럭_정보설정_메뉴_수정_하는_경우() throws IOException {

        // given
        Store store = new Store();
        Menu menu = new Menu(store, "메뉴", 1000L, false);
        em.persist(store);

        List<MenuUpdateDTO> updateMenus =new ArrayList<>();
        MenuUpdateDTO updateMenu = new MenuUpdateDTO();
        updateMenu.setId(menu.getId());
        updateMenu.setName("수정된 메뉴");
        updateMenu.setPrice(2000L);
        MockMultipartFile newImage = new MockMultipartFile("image",
                "updateImage.png",
                "image/png",
                new FileInputStream("/Users/ajyng/Desktop/springmvc-img.png"));
        updateMenu.setImage(newImage);
        updateMenu.setSoldOut(true);
        updateMenus.add(updateMenu);

        List<MenuCreateDTO> createMenus = new ArrayList<>();
        List<MenuDeleteDTO> deleteMenus = new ArrayList<>();
        List<PaymentMethod> payments = new ArrayList<>();

        // when
        storeService.updateStoreInfo(store, "맛난 오징어", Category.Snack, "321-456", "다들 오세요.",
                createMenus, updateMenus, deleteMenus, payments);

        // then
        assertThat(store.getMenuList().get(0).getName()).isEqualTo("수정된 메뉴");
        assertThat(store.getMenuList().get(0).getPrice()).isEqualTo(2000L);
        assertThat(store.getMenuList().get(0).getImage().getUploadFileName()).isEqualTo("updateImage.png");
    }

    @Test
    public void 이미_존재하는_결제수단_추가() throws IOException {

        // given
        Store store = new Store();
        Payment payment = new Payment(store, PaymentMethod.Cash);
        em.persist(store);

        // when

        List<MenuCreateDTO> createMenus = new ArrayList<>();
        List<MenuUpdateDTO> updateMenus = new ArrayList<>();
        List<MenuDeleteDTO> deleteMenus = new ArrayList<>();
        List<PaymentMethod> payments = new ArrayList<>();
        payments.add(PaymentMethod.Cash);

        storeService.updateStoreInfo(store, "맛난 오징어", Category.Snack, "321-456", "다들 오세요.",
                createMenus, updateMenus, deleteMenus, payments);

        // then
        assertThat(store.getPayments()).hasSize(1);
    }

    @Test
    public void 이미_존재하는_결제수단_삭제() throws IOException {

        // given
        Store store = new Store();
        Payment payment1 = new Payment(store, PaymentMethod.Cash);
        Payment payment2 = new Payment(store, PaymentMethod.Card);
        em.persist(store);

        // when

        List<MenuCreateDTO> createMenus = new ArrayList<>();
        List<MenuUpdateDTO> updateMenus = new ArrayList<>();
        List<MenuDeleteDTO> deleteMenus = new ArrayList<>();
        List<PaymentMethod> payments = new ArrayList<>();
        payments.add(PaymentMethod.Cash);

        storeService.updateStoreInfo(store, "맛난 오징어", Category.Snack, "321-456", "다들 오세요.",
                createMenus, updateMenus, deleteMenus, payments);

        // then
        assertThat(store.getPayments()).hasSize(1);
    }
}