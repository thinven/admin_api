package com.thinven.boot.domain.entity.log.userrequest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRequest is a Querydsl query type for UserRequest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRequest extends EntityPathBase<UserRequest<?>> {

    private static final long serialVersionUID = -1655431576L;

    public static final QUserRequest userRequest = new QUserRequest("userRequest");

    public final com.thinven.boot.support.domain.entity.model.QLastTimeEntityModel _super = new com.thinven.boot.support.domain.entity.model.QLastTimeEntityModel(this);

    public final StringPath entitystring = createString("entitystring");

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath requesturi = createString("requesturi");

    public final StringPath rk = createString("rk");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath workdate = createString("workdate");

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QUserRequest(String variable) {
        super((Class) UserRequest.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QUserRequest(Path<? extends UserRequest> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QUserRequest(PathMetadata metadata) {
        super((Class) UserRequest.class, metadata);
    }

}

