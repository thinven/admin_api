package com.thinven.boot.domain.entity.roleset.role;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 2064239428L;

    public static final QRole role = new QRole("role");

    public final com.thinven.boot.support.domain.entity.model.QEntityModel _super = new com.thinven.boot.support.domain.entity.model.QEntityModel(this);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    public final StringPath uid = createString("uid");

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

