package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberModel is a Querydsl query type for MemberModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QMemberModel extends EntityPathBase<MemberModel> {

    private static final long serialVersionUID = -148610653L;

    public static final QMemberModel memberModel = new QMemberModel("memberModel");

    public final QRegTimeEntityModel _super = new QRegTimeEntityModel(this);

    public final StringPath id = createString("id");

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath pk = createString("pk");

    public final StringPath pw = createString("pw");

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    public final StringPath rk = createString("rk");

    public final StringPath uid = createString("uid");

    public QMemberModel(String variable) {
        super(MemberModel.class, forVariable(variable));
    }

    public QMemberModel(Path<? extends MemberModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberModel(PathMetadata metadata) {
        super(MemberModel.class, metadata);
    }

}

