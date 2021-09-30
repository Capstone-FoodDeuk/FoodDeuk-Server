package CapstoneDesign.Server.entity.review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Taste {
    Good("맛있어요!"),
    SoSo("보통이에요"),
    Bad("별로에요");

    private final String taste;
}
