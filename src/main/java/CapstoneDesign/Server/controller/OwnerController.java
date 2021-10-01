package CapstoneDesign.Server.controller;

import CapstoneDesign.Server.config.annotation.TokenUser;
import CapstoneDesign.Server.domain.dto.ApiResponse;
import CapstoneDesign.Server.domain.dto.StoreDTO;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.OwnerUser;
import CapstoneDesign.Server.domain.entity.user.User;
import CapstoneDesign.Server.repository.StoreRepository;
import CapstoneDesign.Server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owner")
public class OwnerController {

    private final StoreRepository storeRepository;
    private final StoreService storeService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public ApiResponse storeConfig(@RequestBody StoreDTO store, @TokenUser User user) throws IOException {
        OwnerUser ownerUser = (OwnerUser) user;
        Store findStore = storeRepository.findStoreByOwner(ownerUser);

        storeService.updateStoreInfo(findStore, store.getName(), store.getCategory(), store.getRegisterNum(),
                store.getDescription(), store.getCreateMenus(), store.getUpdateMenus(), store.getDeleteMenus(),
                store.getPayments());

        return new ApiResponse(HttpStatus.OK, "푸드트럭 정보 설정 성공", null);
    }
}
