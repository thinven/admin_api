package com.thinven.boot.domain.entity.employeeset.employeerole;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployeeRole is a Querydsl query type for EmployeeRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployeeRole extends EntityPathBase<EmployeeRole> {

    private static final long serialVersionUID = -1976643860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployeeRole employeeRole = new QEmployeeRole("employeeRole");

    public final com.thinven.boot.support.domain.entity.model.QEntityModel _super = new com.thinven.boot.support.domain.entity.model.QEntityModel(this);

    public final com.thinven.boot.domain.entity.employeeset.employee.QEmployee employee;

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    public final com.thinven.boot.domain.entity.roleset.role.QRole role;

    public final StringPath uid = createString("uid");

    public final StringPath use = createString("use");

    public QEmployeeRole(String variable) {
        this(EmployeeRole.class, forVariable(variable), INITS);
    }

    public QEmployeeRole(Path<? extends EmployeeRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployeeRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployeeRole(PathMetadata metadata, PathInits inits) {
        this(EmployeeRole.class, metadata, inits);
    }

    public QEmployeeRole(Class<? extends EmployeeRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new com.thinven.boot.domain.entity.employeeset.employee.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.role = inits.isInitialized("role") ? new com.thinven.boot.domain.entity.roleset.role.QRole(forProperty("role")) : null;
    }

}

