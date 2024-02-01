package com.jyp_board.board_app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntityConfig is a Querydsl query type for BaseEntityConfig
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntityConfig extends EntityPathBase<BaseEntityConfig> {

    private static final long serialVersionUID = -1225239385L;

    public static final QBaseEntityConfig baseEntityConfig = new QBaseEntityConfig("baseEntityConfig");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QBaseEntityConfig(String variable) {
        super(BaseEntityConfig.class, forVariable(variable));
    }

    public QBaseEntityConfig(Path<? extends BaseEntityConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntityConfig(PathMetadata metadata) {
        super(BaseEntityConfig.class, metadata);
    }

}

