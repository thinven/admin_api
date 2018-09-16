package com.thinven.boot.support.domain.entity.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageEntityModel is a Querydsl query type for ImageEntityModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QImageEntityModel extends EntityPathBase<ImageEntityModel> {

    private static final long serialVersionUID = 1215392823L;

    public static final QImageEntityModel imageEntityModel = new QImageEntityModel("imageEntityModel");

    public final QFileEntityModel _super = new QFileEntityModel(this);

    public final NumberPath<Long> height = createNumber("height", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    //inherited
    public final StringPath mimetype = _super.mimetype;

    //inherited
    public final StringPath originname = _super.originname;

    //inherited
    public final StringPath phypath = _super.phypath;

    //inherited
    public final NumberPath<Long> size = _super.size;

    public final NumberPath<Long> width = createNumber("width", Long.class);

    public QImageEntityModel(String variable) {
        super(ImageEntityModel.class, forVariable(variable));
    }

    public QImageEntityModel(Path<? extends ImageEntityModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageEntityModel(PathMetadata metadata) {
        super(ImageEntityModel.class, metadata);
    }

}

