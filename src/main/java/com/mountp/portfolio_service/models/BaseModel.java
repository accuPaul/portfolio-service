package com.mountp.portfolio_service.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    @MongoId(FieldType.OBJECT_ID)
    protected String id;

    protected Date createdAt;
    protected Date updatedAt;
}
