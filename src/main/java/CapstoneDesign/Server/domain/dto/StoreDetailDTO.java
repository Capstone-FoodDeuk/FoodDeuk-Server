package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.store.Category;
import CapstoneDesign.Server.domain.entity.store.Location;
import CapstoneDesign.Server.domain.entity.store.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class StoreDetailDTO {

    private String name;
    private Category category;
    private String phoneNumber;
    private Location location;
    private LocalDateTime closeTime;
    private boolean zzimCheck;
    private List<StoreDetailMenuDTO> menuList;
    private List<PaymentMethod> paymentMethods;
    private StoreDetailReviewDTO reviewSummary;
}
