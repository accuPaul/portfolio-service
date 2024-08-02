package com.mountp.portfolio_service.dtos;

import com.mountp.portfolio_service.models.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProjectDto {
    private String name;
    private String description;
    private String url;
    private String source;

    private Set<String> images;
    private Set<String> tags;

    public Project toProject() {
        return new Project().setName(name).setDescription(description).setProjectUrl(url).setProjectSource(source)
                .setImageLinks(images).setProjectTags(tags);
    }

}
