package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntityModel is a Querydsl query type for EntityModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QEntityModel extends EntityPathBase<EntityModel> {

    private static final long serialVersionUID = -250974726L;

    public static final QEntityModel entityModel = new QEntityModel("entityModel");

    public final QRegTimeEntityModel _super = new QRegTimeEntityModel(this);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    public QEntityModel(String variable) {
        super(EntityModel.class, forVariable(variable));
    }

    public QEntityModel(Path<? extends EntityModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityModel(PathMetadata metadata) {
        super(EntityModel.class, metadata);
    }

}

