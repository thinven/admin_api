package com.thinven.boot.domain.entity.employeeset.employeeauth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployeeAuth is a Querydsl query type for EmployeeAuth
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployeeAuth extends EntityPathBase<EmployeeAuth> {

    private static final long serialVersionUID = -323006420L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployeeAuth employeeAuth = new QEmployeeAuth("employeeAuth");

    public final com.thinven.boot.support.domain.entity.model.QMemberModel _super = new com.thinven.boot.support.domain.entity.model.QMemberModel(this);

    public final com.thinven.boot.domain.entity.employeeset.employee.QEmployee employee;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    //inherited
    public final StringPath pk = _super.pk;

    //inherited
    public final StringPath pw = _super.pw;

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    //inherited
    public final StringPath rk = _super.rk;

    public final ListPath<com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole, com.thinven.boot.domain.entity.employeeset.employeerole.QEmployeeRole> roles = this.<com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole, com.thinven.boot.domain.entity.employeeset.employeerole.QEmployeeRole>createList("roles", com.thinven.boot.domain.entity.employeeset.employeerole.EmployeeRole.class, com.thinven.boot.domain.entity.employeeset.employeerole.QEmployeeRole.class, PathInits.DIRECT2);

    //inherited
    public final StringPath uid = _super.uid;

    public QEmployeeAuth(String variable) {
        this(EmployeeAuth.class, forVariable(variable), INITS);
    }

    public QEmployeeAuth(Path<? extends EmployeeAuth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployeeAuth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployeeAuth(PathMetadata metadata, PathInits inits) {
        this(EmployeeAuth.class, metadata, inits);
    }

    public QEmployeeAuth(Class<? extends EmployeeAuth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new com.thinven.boot.domain.entity.employeeset.employee.QEmployee(forProperty("employee")) : null;
    }

}

