package com.thinven.boot.domain.entity.commoncodeset.commoncodegroup;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonCodeGroup is a Querydsl query type for CommonCodeGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommonCodeGroup extends EntityPathBase<CommonCodeGroup> {

    private static final long serialVersionUID = 400914068L;

    public static final QCommonCodeGroup commonCodeGroup = new QCommonCodeGroup("commonCodeGroup");

    public final com.thinven.boot.support.domain.entity.model.QEntityModel _super = new com.thinven.boot.support.domain.entity.model.QEntityModel(this);

    public final NumberPath<Long> cache = createNumber("cache", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath name = createString("name");

    public final NumberPath<Long> ordered = createNumber("ordered", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    public final StringPath uid = createString("uid");

    public final NumberPath<Long> use = createNumber("use", Long.class);

    public QCommonCodeGroup(String variable) {
        super(CommonCodeGroup.class, forVariable(variable));
    }

    public QCommonCodeGroup(Path<? extends CommonCodeGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonCodeGroup(PathMetadata metadata) {
        super(CommonCodeGroup.class, metadata);
    }

}

