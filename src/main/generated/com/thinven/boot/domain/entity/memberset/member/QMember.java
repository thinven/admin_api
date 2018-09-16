package com.thinven.boot.domain.entity.memberset.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1942457888L;

    public static final QMember member = new QMember("member1");

    public final com.thinven.boot.support.domain.entity.model.QMemberModel _super = new com.thinven.boot.support.domain.entity.model.QMemberModel(this);

    public final NumberPath<Long> goodbye = createNumber("goodbye", Long.class);

    public final DateTimePath<java.util.Date> goodbyetime = createDateTime("goodbyetime", java.util.Date.class);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath nick = createString("nick");

    //inherited
    public final StringPath pk = _super.pk;

    //inherited
    public final StringPath pw = _super.pw;

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    //inherited
    public final StringPath rk = _super.rk;

    //inherited
    public final StringPath uid = _super.uid;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

