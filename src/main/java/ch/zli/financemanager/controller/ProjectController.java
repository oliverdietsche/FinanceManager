package ch.zli.financemanager.controller;

import ch.zli.financemanager.entity.Project;
import ch.zli.financemanager.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("title")
    @ResponseStatus(HttpStatus.OK)
    public Project getProjectByTitle(@Valid @RequestHeader String title) {
        return projectService.findProjectByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createEntry(@Valid @RequestBody Project project) {
        return projectService.createProject(project);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable long id) throws Exception {
        projectService.deleteProjectById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project updateEntry(@Valid @RequestBody Project project, @PathVariable long id) throws Exception {
        return projectService.updateProject(project, id);
    }
}
