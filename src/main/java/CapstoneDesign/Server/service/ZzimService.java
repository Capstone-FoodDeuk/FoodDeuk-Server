package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.entity.Zzim;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.user.GuestUser;
import CapstoneDesign.Server.repository.ZzimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZzimService {

    private final ZzimRepository zzimRepository;

    public boolean checkZzimStore(GuestUser user, Store store) {

        Zzim zzim = zzimRepository.findZzimByStoreAndUser(store, user);
        return zzim != null ? true : false;
    }

    public void zzimStore(GuestUser user, Store store) {

        Zzim zzim = zzimRepository.findZzimByStoreAndUser(store, user);
        if (zzim == null) {
            zzimRepository.save(new Zzim(user, store));
            return;
        }
        zzimRepository.deleteById(zzim.getId());
    }
}