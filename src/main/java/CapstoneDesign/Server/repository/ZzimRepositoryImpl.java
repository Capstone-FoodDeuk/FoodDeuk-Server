package CapstoneDesign.Server.repository;

import CapstoneDesign.Server.domain.dto.UserPageZzimDTO;
import CapstoneDesign.Server.domain.entity.QZzim;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static CapstoneDesign.Server.domain.entity.QZzim.*;

@RequiredArgsConstructor
public class ZzimRepositoryImpl implements ZzimRepostiroyCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserPageZzimDTO> findZzimsByUserId(Long id) {

        return queryFactory
                .select(Projections.fields(UserPageZzimDTO.class,
                        zzim.store.name,
                        zzim.store.category,
                        zzim.store.location,
                        zzim.store.closeTime.as("lastActivateTime"),
                        zzim.isAlarmActive))
                .from(zzim)
                .where(zzim.user.id.eq(id))
                .fetch();
    }
}
