package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPageModel is a Querydsl query type for PageModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QPageModel extends EntityPathBase<PageModel> {

    private static final long serialVersionUID = 1620830158L;

    public static final QPageModel pageModel = new QPageModel("pageModel");

    public final QSecurityModel _super = new QSecurityModel(this);

    public QPageModel(String variable) {
        super(PageModel.class, forVariable(variable));
    }

    public QPageModel(Path<? extends PageModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPageModel(PathMetadata metadata) {
        super(PageModel.class, metadata);
    }

}

