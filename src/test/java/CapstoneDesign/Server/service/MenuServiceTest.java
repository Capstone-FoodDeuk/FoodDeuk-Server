package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.dto.MenuCreateDTO;
import CapstoneDesign.Server.domain.entity.store.ImageFile;
import CapstoneDesign.Server.domain.entity.store.Menu;
import CapstoneDesign.Server.domain.entity.store.Store;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MenuServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MenuService menuService;

    @Test
    public void 이미지_경로_확인() {

        String filename = "this_is_test_image";
        String result = menuService.getFullFilePath(filename);
        System.out.println("result = " + result);
    }

    @Test
    public void 이미지_업로드_성공() throws IOException {

        // given
        MockMultipartFile file = new MockMultipartFile("image",
                "test.png",
                "image/png",
                new FileInputStream("/Users/ajyng/Desktop/logo.png"));

        // when
        ImageFile result = menuService.storeImageFile(file);

        // then
        // 실제 파일이 저장되었는지 확인!
        System.out.println(result.getStoreFileName());
        System.out.println(result.getUploadFileName());
    }

    @Test
    public void 메뉴_생성_성공() throws IOException {

        // given
        Store store = new Store();

        MenuCreateDTO menu = new MenuCreateDTO();
        menu.setName("타코야끼");
        menu.setPrice(3000L);
        menu.setSoldOut(false);
        MockMultipartFile image = new MockMultipartFile("image",
                "test.png",
                "image/png",
                new FileInputStream("/Users/ajyng/Desktop/logo.png"));
        menu.setImage(image);

        // when
        menuService.createMenu(menu, store);

        // then
        assertThat(store.getMenuList()).hasSize(1);
        assertThat(store.getMenuList().get(0).getName()).isEqualTo("타코야끼");
        assertThat(store.getMenuList().get(0).getPrice()).isEqualTo(3000L);
        assertThat(store.getMenuList().get(0).getSoldOut()).isEqualTo(false);
        assertThat(store.getMenuList().get(0).getImage().getUploadFileName()).isEqualTo("test.png");
    }
}