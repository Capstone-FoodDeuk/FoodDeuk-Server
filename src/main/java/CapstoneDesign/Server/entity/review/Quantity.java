package CapstoneDesign.Server.entity.review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Quantity {
    Enough("만족해요!"),
    SoSo("보통이에요"),
    Bad("부족해요");

    private final String quantity;
}