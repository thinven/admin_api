package com.thinven.boot.domain.entity.sequence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSequence is a Querydsl query type for Sequence
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSequence extends EntityPathBase<Sequence> {

    private static final long serialVersionUID = -1792813338L;

    public static final QSequence sequence = new QSequence("sequence");

    public final com.thinven.boot.support.domain.entity.model.QBaseModel _super = new com.thinven.boot.support.domain.entity.model.QBaseModel(this);

    public final StringPath name = createString("name");

    public final NumberPath<Long> nextvalue = createNumber("nextvalue", Long.class);

    public QSequence(String variable) {
        super(Sequence.class, forVariable(variable));
    }

    public QSequence(Path<? extends Sequence> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSequence(PathMetadata metadata) {
        super(Sequence.class, metadata);
    }

}

