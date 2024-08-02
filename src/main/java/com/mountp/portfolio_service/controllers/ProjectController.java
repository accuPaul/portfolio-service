package com.mountp.portfolio_service.controllers;

import com.mountp.portfolio_service.dtos.ProjectDto;
import com.mountp.portfolio_service.dtos.TagListDto;
import com.mountp.portfolio_service.models.Project;
import com.mountp.portfolio_service.repositories.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/")
public class ProjectController {
    ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping("projects")
    public ResponseEntity<Project> createProject(@RequestHeader("Authorization") String authToken, @RequestBody ProjectDto projectDto) {
        System.out.println(authToken);
        Project projectCreated = projectRepository.save(projectDto.toProject());

        return new ResponseEntity<>(projectCreated, HttpStatus.CREATED);
    }

    @GetMapping("projects")
    public ResponseEntity<List<Project>> findAll() {
        return new ResponseEntity<>(projectRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("projects/tag/{tag}")
    public ResponseEntity<List<Project>> findProjectsByTag(@PathVariable String tag) {
        return new ResponseEntity<>(projectRepository.findAllByProjectTagsContainsIgnoreCase(tag),HttpStatus.OK);
    }

    @GetMapping("projects/{id}")
    public ResponseEntity<Project> findOne(@PathVariable String id) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalProject.get(),HttpStatus.OK);
    }

    @PutMapping("projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody ProjectDto projectDto) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        Project updatedProject = optionalProject.get()
                .setName(projectDto.getName())
                .setProjectSource(projectDto.getSource())
                .setProjectUrl(projectDto.getUrl())
                .setDescription(projectDto.getDescription())
                .setProjectTags(projectDto.getTags())
                .setImageLinks(projectDto.getImages());

        updatedProject = projectRepository.save(updatedProject);

        return new ResponseEntity<>(updatedProject,HttpStatus.OK);
    }

    @PutMapping("projects/{id}/{tag}")
    public ResponseEntity<Project> addProjectTags(@PathVariable String id, @RequestBody TagListDto tagList) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        Set<String> updatedTagList = optionalProject.get().getProjectTags();

        updatedTagList.addAll(tagList.getTagList());

            Project updatedProject = projectRepository.save(optionalProject.get().setProjectTags(updatedTagList));
            return new ResponseEntity<>(updatedProject,HttpStatus.OK);

    }

    @DeleteMapping("projects/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable String id) {
        projectRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
