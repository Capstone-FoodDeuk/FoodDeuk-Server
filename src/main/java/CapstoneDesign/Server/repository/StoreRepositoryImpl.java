package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.HomeStoreDTO;
import CapstoneDesign.Server.domain.entity.store.QMenu;
import CapstoneDesign.Server.domain.entity.store.QStore;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static CapstoneDesign.Server.domain.entity.store.QMenu.menu;
import static CapstoneDesign.Server.domain.entity.store.QStore.store;
import static com.querydsl.core.types.dsl.MathExpressions.*;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HomeStoreDTO> findActiveStoresByLocationLessThanDistance(double latitude, double longitude, double distance) {

        List<Long> storeIds = queryFactory
                .select(store.id)
                .from(store)
                .groupBy(store.id, store.location.latitude, store.location.longitude)
                .having(Expressions.predicate(Ops.LOE, Expressions.asNumber(getDistanceExpression(latitude, longitude)), Expressions.asNumber(distance)))
                .fetch();

        return queryFactory.select(Projections.fields(HomeStoreDTO.class,
                store.id,
                store.category,
                store.location.latitude,
                store.location.longitude))
                .from(store).distinct()
                .innerJoin(store.menuList, menu).fetchJoin()
                .where(
                        store.id.in(storeIds),
                        store.isActive.eq(true)
                )
                .fetch();
    }

    /**
     * 위도 (latitude), 경도 (longitude)가 주어졌을때 거리 계산 공식.
     * -
     * 6371 * acos(cos(radians(:latitude)) * cos(radians(store.latitude)) * cos(radians(store.longitude)
     * - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(store.latitude)))) as distance
     */
    private NumberExpression<Double> getDistanceExpression(double latitude, double longitude) {
        return acos(sin(radians(Expressions.constant(latitude)))
                .multiply(sin(radians(store.location.latitude)))
                .add(cos(radians(Expressions.constant(latitude)))
                        .multiply(cos(radians(store.location.latitude)))
                        .multiply(cos(radians(Expressions.constant(longitude)).subtract(radians(store.location.longitude))))
                )).multiply(6371);
    }
}
