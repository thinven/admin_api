package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRegTimeEntityModel is a Querydsl query type for RegTimeEntityModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QRegTimeEntityModel extends EntityPathBase<RegTimeEntityModel> {

    private static final long serialVersionUID = -1968154287L;

    public static final QRegTimeEntityModel regTimeEntityModel = new QRegTimeEntityModel("regTimeEntityModel");

    public final QLastTimeEntityModel _super = new QLastTimeEntityModel(this);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final DateTimePath<java.util.Date> regdate = createDateTime("regdate", java.util.Date.class);

    public QRegTimeEntityModel(String variable) {
        super(RegTimeEntityModel.class, forVariable(variable));
    }

    public QRegTimeEntityModel(Path<? extends RegTimeEntityModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegTimeEntityModel(PathMetadata metadata) {
        super(RegTimeEntityModel.class, metadata);
    }

}

