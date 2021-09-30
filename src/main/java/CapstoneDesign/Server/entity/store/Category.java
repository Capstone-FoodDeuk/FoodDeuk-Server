package CapstoneDesign.Server.entity.store;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    Takoyaki("타코야끼"),
    FishBread("붕어빵"),
    Snack("분식"),
    Fruit("과일"),
    Chestnuts("군밤");

    private final String category;
}