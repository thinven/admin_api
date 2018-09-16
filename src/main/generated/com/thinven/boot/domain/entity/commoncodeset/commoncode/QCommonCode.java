package com.thinven.boot.domain.entity.commoncodeset.commoncode;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommonCode is a Querydsl query type for CommonCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommonCode extends EntityPathBase<CommonCode> {

    private static final long serialVersionUID = 131418498L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommonCode commonCode = new QCommonCode("commonCode");

    public final com.thinven.boot.support.domain.entity.model.QEntityModel _super = new com.thinven.boot.support.domain.entity.model.QEntityModel(this);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.QCommonCodeGroup commoncodegroup;

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath name = createString("name");

    public final NumberPath<Long> order = createNumber("order", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    public final StringPath uid = createString("uid");

    public final NumberPath<Long> use = createNumber("use", Long.class);

    public QCommonCode(String variable) {
        this(CommonCode.class, forVariable(variable), INITS);
    }

    public QCommonCode(Path<? extends CommonCode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommonCode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommonCode(PathMetadata metadata, PathInits inits) {
        this(CommonCode.class, metadata, inits);
    }

    public QCommonCode(Class<? extends CommonCode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commoncodegroup = inits.isInitialized("commoncodegroup") ? new com.thinven.boot.domain.entity.commoncodeset.commoncodegroup.QCommonCodeGroup(forProperty("commoncodegroup")) : null;
    }

}

