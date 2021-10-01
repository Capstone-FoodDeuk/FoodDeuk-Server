package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.StoreDetailMenuDTO;
import CapstoneDesign.Server.domain.entity.store.QMenu;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static CapstoneDesign.Server.domain.entity.store.QMenu.menu;

@RequiredArgsConstructor
public class MenuRepositryImpl implements MenuRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StoreDetailMenuDTO> findMenuListByStoreId(Long id) {

        return queryFactory
                .select(Projections.fields(StoreDetailMenuDTO.class,
                        menu.name,
                        menu.price,
                        menu.soldOut))
                .from(menu)
                .where(menu.store.id.eq(id))
                .fetch();
    }
}
