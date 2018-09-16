package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLastTimeEntityModel is a Querydsl query type for LastTimeEntityModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QLastTimeEntityModel extends EntityPathBase<LastTimeEntityModel> {

    private static final long serialVersionUID = -71064713L;

    public static final QLastTimeEntityModel lastTimeEntityModel = new QLastTimeEntityModel("lastTimeEntityModel");

    public final QPageModel _super = new QPageModel(this);

    public final DateTimePath<java.util.Date> lastdate = createDateTime("lastdate", java.util.Date.class);

    public QLastTimeEntityModel(String variable) {
        super(LastTimeEntityModel.class, forVariable(variable));
    }

    public QLastTimeEntityModel(Path<? extends LastTimeEntityModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLastTimeEntityModel(PathMetadata metadata) {
        super(LastTimeEntityModel.class, metadata);
    }

}

