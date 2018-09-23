package com.thinven.boot.domain.entity.employeeset.employee;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 1220958476L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployee employee = new QEmployee("employee");

    public final com.thinven.boot.support.domain.entity.model.QEntityModel _super = new com.thinven.boot.support.domain.entity.model.QEntityModel(this);

    public final StringPath birthday = createString("birthday");

    public final NumberPath<Long> delete = createNumber("delete", Long.class);

    public final StringPath email = createString("email");

    public final com.thinven.boot.domain.entity.employeeset.employeeauth.QEmployeeAuth employeeAuth;

    public final StringPath firstname = createString("firstname");

    public final NumberPath<Long> gender = createNumber("gender", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> lastdate = _super.lastdate;

    public final StringPath lastname = createString("lastname");

    public final StringPath phone = createString("phone");

    //inherited
    public final DateTimePath<java.util.Date> regdate = _super.regdate;

    public final StringPath uid = createString("uid");

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployee(PathMetadata metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employeeAuth = inits.isInitialized("employeeAuth") ? new com.thinven.boot.domain.entity.employeeset.employeeauth.QEmployeeAuth(forProperty("employeeAuth"), inits.get("employeeAuth")) : null;
    }

}

