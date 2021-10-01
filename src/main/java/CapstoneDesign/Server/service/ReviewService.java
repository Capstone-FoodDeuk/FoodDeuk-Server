package CapstoneDesign.Server.service;

import CapstoneDesign.Server.domain.dto.ReviewContentDTO;
import CapstoneDesign.Server.domain.dto.StoreDetailReviewDTO;
import CapstoneDesign.Server.domain.entity.review.Kindness;
import CapstoneDesign.Server.domain.entity.review.Quantity;
import CapstoneDesign.Server.domain.entity.review.Taste;
import CapstoneDesign.Server.domain.entity.store.Store;
import CapstoneDesign.Server.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public StoreDetailReviewDTO getReviewSummary(Store store) {
        List<ReviewContentDTO> contents = reviewRepository.findReviewContentsByStoreId(store.getId());

        Long userCount = contents.stream().count(); // 리뷰를 남긴 전체 유저 수
        Long totalScore = contents.stream().mapToLong(ReviewContentDTO::getScore).sum(); // 전체 평점 합계

        Map<Taste, Long> tasteMap = contents.stream().collect(
                Collectors.groupingBy(ReviewContentDTO::getTaste, Collectors.counting()));

        Map<Quantity, Long> quantityMap = contents.stream().collect(
                Collectors.groupingBy(ReviewContentDTO::getQuantity, Collectors.counting()));

        Map<Kindness, Long> kindnessMap = contents.stream().collect(
                Collectors.groupingBy(ReviewContentDTO::getKindness, Collectors.counting()));

        // 가장 많은 유저가 선택한 '맛' 평가
        Taste taste = tasteMap.entrySet().stream().max((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                if (o1.getKey() == Taste.Good || o2.getKey() == Taste.Good)
                    return (o1.getKey() == Taste.Good) ? 1 : -1;
                else if (o1.getKey() == Taste.SoSo || o2.getKey() == Taste.SoSo)
                    return (o1.getKey() == Taste.SoSo) ? 1 : -1;
            }
            return o1.getValue() > o2.getValue() ? 1 : -1;
        }).get().getKey();
        // 해당 '맛'을 선택한 유저의 수
        Long tasteCount = Collections.max(tasteMap.values());

        // 가장 많은 유저가 선택한 '양' 평가
        Quantity quantity = quantityMap.entrySet().stream().max((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                if (o1.getKey() == Quantity.Enough || o2.getKey() == Quantity.Enough)
                    return (o1.getKey() == Quantity.Enough) ? 1 : -1;
                else if (o1.getKey() == Quantity.SoSo || o2.getKey() == Quantity.SoSo)
                    return (o1.getKey() == Quantity.SoSo) ? 1 : -1;
            }
            return o1.getValue() > o2.getValue() ? 1 : -1;
        }).get().getKey();
        // 해당 '양'을 선택한 유저의 수
        Long quantityCount = Collections.max(quantityMap.values());

        // 가장 많은 유저가 선택한 '친절도' 평가
        Kindness kindness = kindnessMap.entrySet().stream().max((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                if (o1.getKey() == Kindness.Kind || o2.getKey() == Kindness.Kind)
                    return (o1.getKey() == Kindness.Kind) ? 1 : -1;
                else if (o1.getKey() == Kindness.SoSo || o2.getKey() == Kindness.SoSo)
                    return (o1.getKey() == Kindness.SoSo) ? 1 : -1;
            }
            return o1.getValue() > o2.getValue() ? 1 : -1;
        }).get().getKey();
        // 해당 '친절도'를 선택한 유저의 수
        Long kindnessCount = Collections.max(kindnessMap.values());

        Map<Taste, Long> tasteScore = new ConcurrentHashMap<>();
        Map<Quantity, Long> quantityScore = new ConcurrentHashMap<>();
        Map<Kindness, Long> kindnessScore = new ConcurrentHashMap<>();
        tasteScore.put(taste, tasteCount);
        quantityScore.put(quantity, quantityCount);
        kindnessScore.put(kindness, kindnessCount);

        return new StoreDetailReviewDTO(userCount, totalScore, tasteScore, quantityScore, kindnessScore);
    }
}
