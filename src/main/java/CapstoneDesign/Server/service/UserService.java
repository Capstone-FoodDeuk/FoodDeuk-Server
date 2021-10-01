package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.repository.ZzimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ZzimRepository zzimRepository;

    public boolean checkZzimStore(GuestUser user, Store store) {

        return zzimRepository.findZzimsByUser(user).stream()
                .anyMatch(z -> z.getStore().equals(user));
    }
}