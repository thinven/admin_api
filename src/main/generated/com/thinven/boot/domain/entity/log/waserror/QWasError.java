package com.thinven.boot.domain.entity.log.waserror;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWasError is a Querydsl query type for WasError
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWasError extends EntityPathBase<WasError<?>> {

    private static final long serialVersionUID = -947575716L;

    public static final QWasError wasError = new QWasError("wasError");

    public final com.thinven.boot.support.domain.entity.model.QLastTimeEntityModel _super = new com.thinven.boot.support.domain.entity.model.QLastTimeEntityModel(this);

    public final StringPath entitystring = createString("entitystring");

    public final StringPath errmsg = createString("errmsg");

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath requesturi = createString("requesturi");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath simplemsg = createString("simplemsg");

    public final StringPath workdate = createString("workdate");

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QWasError(String variable) {
        super((Class) WasError.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QWasError(Path<? extends WasError> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QWasError(PathMetadata metadata) {
        super((Class) WasError.class, metadata);
    }

}

