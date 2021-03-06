package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.dto.HomeStoreDTO;
import CapstoneDesign.Server.domain.dto.MenuCreateDTO;
import CapstoneDesign.Server.domain.dto.MenuDeleteDTO;
import CapstoneDesign.Server.domain.dto.MenuUpdateDTO;
import CapstoneDesign.Server.domain.entity.store.*;
import CapstoneDesign.Server.exception.DuplicatedMenuException;
import CapstoneDesign.Server.exception.NotFoundMenuException;
import CapstoneDesign.Server.repository.MenuRepository;
import CapstoneDesign.Server.repository.PaymentRepository;
import CapstoneDesign.Server.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final PaymentRepository paymentRepository;
    private final MenuService menuService;

    private static final double LIMIT_DISTANCE = 2.0;

    @Transactional
    public void updateStoreInfo(Store store, String name, Category category, String registerNum, String description,
                                List<MenuCreateDTO> createMenus, List<MenuUpdateDTO> updateMenus,
                                List<MenuDeleteDTO> deleteMenus, List<PaymentMethod> payments) throws IOException {

        store.updateStoreInfo(name, category, registerNum, description);

        /*
        메뉴 추가, 삭제, 수정 구현
         */
        if (!createMenus.isEmpty()) {
            for (MenuCreateDTO newMenu : createMenus) {
                menuService.createMenu(newMenu, store);
            }
        }
        if (!deleteMenus.isEmpty()) {
            for (MenuDeleteDTO deleteMenu : deleteMenus) {
                Menu findMenu = menuRepository.findById(deleteMenu.getId()).orElseThrow(() -> new NotFoundMenuException());
                store.getMenuList().remove(findMenu);
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
        for (PaymentMethod paymentMethod : payments) {
            Payment payment = paymentRepository.findPaymentByStoreAndMethod(store, paymentMethod);
            if (payment == null) {
                Payment newPayment = new Payment(store, paymentMethod);
                paymentRepository.save(newPayment);
            }
        }
        List<Payment> removePayment = paymentRepository.findPaymentsByStore(store).stream()
                .filter(payment -> !payments.contains(payment.getMethod()))
                .collect(Collectors.toList());
        if (!removePayment.isEmpty()) {
            for (Payment payment : removePayment) {
                store.getPayments().remove(payment);
            }
        }

    }

    public List<HomeStoreDTO> getNearStore(Double latitude, Double longitude) {

        List<HomeStoreDTO> nearStores = storeRepository.findActiveStoresByLocationLessThanDistance(
                latitude, longitude, LIMIT_DISTANCE);

        return nearStores;
    }
}
