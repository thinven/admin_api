package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVOTypeEntityModel is a Querydsl query type for VOTypeEntityModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QVOTypeEntityModel extends EntityPathBase<VOTypeEntityModel> {

    private static final long serialVersionUID = -1925932153L;

    public static final QVOTypeEntityModel vOTypeEntityModel = new QVOTypeEntityModel("vOTypeEntityModel");

    public final QLastTimeEntityModel _super = new QLastTimeEntityModel(this);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public QVOTypeEntityModel(String variable) {
        super(VOTypeEntityModel.class, forVariable(variable));
    }

    public QVOTypeEntityModel(Path<? extends VOTypeEntityModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVOTypeEntityModel(PathMetadata metadata) {
        super(VOTypeEntityModel.class, metadata);
    }

}

