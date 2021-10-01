package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.dto.MenuCreateDTO;
import CapstoneDesign.Server.domain.dto.MenuUpdateDTO;
import CapstoneDesign.Server.domain.entity.store.*;
import CapstoneDesign.Server.exception.NotFoundMenuException;
import CapstoneDesign.Server.exception.NotFoundStoreException;
import CapstoneDesign.Server.repository.MenuRepository;
import CapstoneDesign.Server.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final MenuService menuService;

    @Transactional
    public void updateStoreInfo(Long storeId, String name, Category category, String registerNum, String description,
                                List<MenuCreateDTO> createMenus, List<MenuUpdateDTO> updateMenus, List<MenuUpdateDTO> deleteMenus,
                                List<PaymentMethod> createPayment, List<PaymentMethod> updatePayment) throws IOException {

        Store findStore = storeRepository.findById(storeId).orElseThrow(() -> new NotFoundStoreException());
        findStore.updateStoreInfo(name, category, registerNum, description);

        /*
        메뉴 추가, 삭제, 수정 구현
         */
        if (!createMenus.isEmpty()) {
            for (MenuCreateDTO newMenu : createMenus) {
                menuService.createMenu(newMenu, findStore);
            }
        }
        if (!deleteMenus.isEmpty()) {
            for (MenuUpdateDTO deleteMenu : deleteMenus) {
                menuRepository.deleteById(deleteMenu.getId());
            }
        }
        if (!updateMenus.isEmpty()) {
            for (MenuUpdateDTO updateMenu : updateMenus) {
                Menu menu = menuRepository.findById(updateMenu.getId()).orElseThrow(() -> new NotFoundMenuException());
                menu.updateMenuInfo(updateMenu.getName(), updateMenu.getPrice(), updateMenu.getSoldOut());

                ImageFile imageFile = menuService.storeImageFile(updateMenu.getImage());
                menu.addImageFile(imageFile);
            }
        }

        /*
        결제수단 추가, 삭제 구현
         */
    }
}
