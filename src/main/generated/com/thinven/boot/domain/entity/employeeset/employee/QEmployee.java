package com.thinven.boot.domain.entity.employeeset.employee;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 1220958476L;

    public static final QEmployee employee = new QEmployee("employee");

    public final com.thinven.boot.support.domain.entity.model.QEntityModel _super = new com.thinven.boot.support.domain.entity.model.QEntityModel(this);

    public final StringPath birthday = createString("birthday");

    public final StringPath email = createString("email");

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
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

