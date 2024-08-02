package com.mountp.portfolio_service.models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "projects")
@Accessors(chain = true)
@Data
public class Project {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    private String name;

    private String description;
    private String projectUrl;
    private String projectSource;
    private Set<String> imageLinks;

    private Set<String> projectTags;

    public Project() {
        this.imageLinks = new HashSet<>();
        this.projectTags = new HashSet<>();
    }
}
