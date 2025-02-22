package com.goorm.bakkuyoungapi.domain.member.dao;

import com.goorm.bakkuyoungapi.domain.member.domain.Member;
import com.goorm.bakkuyoungapi.domain.member.domain.QMember;
import com.goorm.bakkuyoungapi.domain.member.dto.response.MemberDetail;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public MemberQuerydslRepository(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }


    QMember member = QMember.member;


    public List<MemberDetail> findAllMembers() {
        return queryFactory
                .select(Projections.constructor(MemberDetail.class,
                        member.memberNo,
                        member.id,
                        member.memberName))
                .from(member)
                .fetch();
    }


}
