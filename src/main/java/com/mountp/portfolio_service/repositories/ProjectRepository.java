package com.mountp.portfolio_service.repositories;

import com.mountp.portfolio_service.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findAllByProjectTagsContainsIgnoreCase(String tag);
}
