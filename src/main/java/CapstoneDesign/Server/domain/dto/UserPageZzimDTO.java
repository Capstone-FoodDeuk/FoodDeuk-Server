package CapstoneDesign.Server.domain.dto;

import CapstoneDesign.Server.domain.entity.store.Category;
import CapstoneDesign.Server.domain.entity.store.Location;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPageZzimDTO {

    private String name;
    private Category category;
    private Location location;
    private LocalDateTime lastActivateTime;
    private boolean isAlarmActive;
}