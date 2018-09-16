package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileEntityModel is a Querydsl query type for FileEntityModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QFileEntityModel extends EntityPathBase<FileEntityModel> {

    private static final long serialVersionUID = -2005172002L;

    public static final QFileEntityModel fileEntityModel = new QFileEntityModel("fileEntityModel");

    public final QVOTypeEntityModel _super = new QVOTypeEntityModel(this);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath mimetype = createString("mimetype");

    public final StringPath originname = createString("originname");

    public final StringPath phypath = createString("phypath");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public QFileEntityModel(String variable) {
        super(FileEntityModel.class, forVariable(variable));
    }

    public QFileEntityModel(Path<? extends FileEntityModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileEntityModel(PathMetadata metadata) {
        super(FileEntityModel.class, metadata);
    }

}

