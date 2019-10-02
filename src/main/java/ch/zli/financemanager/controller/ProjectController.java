package ch.zli.financemanager.controller;

import ch.zli.financemanager.entity.Project;
import ch.zli.financemanager.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * This method uses the projectservice to get all projects stored in the database
     * @return a list of all projects stored in the database
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

    /**
     * this method takes he title in the request header an searches the project by name in the database
     * @param title
     * @return the project in the db with the defined title
     */
    @GetMapping("title")
    @ResponseStatus(HttpStatus.OK)
    public Project getProjectByTitle(@Valid @RequestHeader String title) {
        return projectService.findProjectByTitle(title);
    }

    /**
     * takes a project as a parameter and stores it in the database
     * @param project
     * @return returns the project, which got stored in the database
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@Valid @RequestBody Project project) {
        return projectService.createProject(project);
    }

    /**
     * this method deletes a project by its id and throws an exception if there isn't a project with the defined id
     * @param id
     * @throws Exception
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable long id) throws Exception {
        projectService.deleteProjectById(id);
    }

    /**
     * this method updates a project by its id. if it doesn't exist, it'll throw an exception.
     * @param project
     * @param id
     * @return the updated project
     * @throws Exception
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project updateProject(@Valid @RequestBody Project project, @PathVariable long id) throws Exception {
        return projectService.updateProject(project, id);
    }

    /**
     * this method is used because plain html can't do method="delete" and if you use th:method="delete", which would work, you can't redirect the user in order to show the changes.
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("delete/{id}")
    public String postDeleteProject(@PathVariable long id) throws Exception {
        deleteProject(id);
        return "redirect:/fe/projects";
    }

    /**
     * this method is used because plain html can't do method="update" and if you use th:method="update", which would work, you can't redirect the user in order to show the changes.
     * @param project
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("update/{id}")
    public String postUpdateProject(@ModelAttribute Project project, @PathVariable long id) throws Exception {
        updateProject(project, id);
        return "redirect:/fe/projects";
    }

    /**
     * this method is used to redirect the user after creating a project.
     * @param project
     * @return
     * @throws Exception
     */
    @PostMapping("create")
    public String postCreateProject(@ModelAttribute Project project) throws Exception {
        createProject(project);
        return "redirect:/fe/projects";
    }
}
