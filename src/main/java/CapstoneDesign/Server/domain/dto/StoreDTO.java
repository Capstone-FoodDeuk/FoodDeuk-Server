package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.store.Category;
import CapstoneDesign.Server.domain.entity.store.PaymentMethod;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class StoreDTO {

    @NotBlank
    private String name;

    @NotBlank
    private Category category;

    @NotBlank
    private String registerNum;
    private String description;

    private List<MenuCreateDTO> createMenus;
    private List<MenuUpdateDTO> updateMenus;
    private List<MenuUpdateDTO> deleteMenus;

    private List<PaymentMethod> createPayment;
    private List<PaymentMethod> deletePayment;
}
