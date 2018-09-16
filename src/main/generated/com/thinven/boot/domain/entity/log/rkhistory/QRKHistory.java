package com.thinven.boot.domain.entity.log.rkhistory;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRKHistory is a Querydsl query type for RKHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRKHistory extends EntityPathBase<RKHistory> {

    private static final long serialVersionUID = -198081034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRKHistory rKHistory = new QRKHistory("rKHistory");

    public final com.thinven.boot.support.domain.entity.model.QLastTimeEntityModel _super = new com.thinven.boot.support.domain.entity.model.QLastTimeEntityModel(this);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final com.thinven.boot.domain.entity.memberset.member.QMember member;

    public final StringPath nick = createString("nick");

    public final StringPath pk = createString("pk");

    public final StringPath rk = createString("rk");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath workdate = createString("workdate");

    public QRKHistory(String variable) {
        this(RKHistory.class, forVariable(variable), INITS);
    }

    public QRKHistory(Path<? extends RKHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRKHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRKHistory(PathMetadata metadata, PathInits inits) {
        this(RKHistory.class, metadata, inits);
    }

    public QRKHistory(Class<? extends RKHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.thinven.boot.domain.entity.memberset.member.QMember(forProperty("member")) : null;
    }

}

