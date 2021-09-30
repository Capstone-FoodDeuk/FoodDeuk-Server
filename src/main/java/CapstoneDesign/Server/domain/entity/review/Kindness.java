package CapstoneDesign.Server.domain.entity.review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Kindness {
    Kind("친절해요!"),
    SoSo("보통이에요"),
    Bad("불친절해요");

    private final String kindness;
}