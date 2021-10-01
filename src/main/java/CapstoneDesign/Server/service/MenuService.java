package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.dto.MenuCreateDTO;
import CapstoneDesign.Server.domain.entity.store.Menu;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.domain.entity.store.ImageFile;
import CapstoneDesign.Server.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Value("${file.dir}")
    private String fileDir;

    public String getFullFilePath(String filename) {
        return fileDir + filename;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public ImageFile storeImageFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullFilePath(storeFileName)));
        return new ImageFile(originalFilename, storeFileName);
    }

    public void createMenu(MenuCreateDTO menu, Store store) throws IOException {
        Menu newMenu = new Menu(store, menu.getName(), menu.getPrice(), menu.getSoldOut());
        ImageFile imageFile = storeImageFile(menu.getImage());
        newMenu.addImageFile(imageFile);

        menuRepository.save(newMenu);
    }
}
