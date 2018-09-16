package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSecurityModel is a Querydsl query type for SecurityModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QSecurityModel extends EntityPathBase<SecurityModel> {

    private static final long serialVersionUID = -1499725891L;

    public static final QSecurityModel securityModel = new QSecurityModel("securityModel");

    public final QBaseModel _super = new QBaseModel(this);

    public QSecurityModel(String variable) {
        super(SecurityModel.class, forVariable(variable));
    }

    public QSecurityModel(Path<? extends SecurityModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSecurityModel(PathMetadata metadata) {
        super(SecurityModel.class, metadata);
    }

}

