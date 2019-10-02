package ch.zli.financemanager.controller;

import ch.zli.financemanager.entity.Project;
import ch.zli.financemanager.entity.User;
import ch.zli.financemanager.service.ProjectService;
import ch.zli.financemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fe")
public class FrontendController {

    private ProjectService projectService;
    private UserService userService;

    @Autowired
    public FrontendController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    /**
     * this method gets all projects from the database and adds the to the model. with the corresponding html file, the projects will get displayed in the frontend
     * @param model
     * @return the name of the html file stored in the templates folder
     */
    @GetMapping("projects")
    public String loadProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "listProjects";
    }

    /**
     * this method adds a project to the model by it's id and returns the name of the edit form html file
     * @param id
     * @param model
     * @return the name of the html file stored in the templates folder
     */
    @GetMapping("projects/edit/{id}")
    public String editProject(@PathVariable long id, Model model) {
        model.addAttribute("project", projectService.findProjectById(id).get());
        return "editProject";
    }

    /**
     * this method adds a empty project to the model and returns the name of the new project form html
     * @param model
     * @return the name of the html file stored in the templates folder
     */
    @GetMapping("projects/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        return "newProject";
    }

    /**
     * this method gets all users from the database and adds the to the model. with the corresponding html file, the users will get displayed in the frontend
     * @param model
     * @return the name of the html file stored in the templates folder
     */
    @GetMapping("users")
    public String loadUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "listUsers";
    }

    /**
     * this method adds a user to the model by it's id and returns the name of the edit form html file
     * @param id
     * @param model
     * @return the name of the html file stored in the templates folder
     */
    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findUserById(id).get());
        return "editUser";
    }

    /**
     * this method adds a empty user to the model and returns the name of the new user form html
     * @param model
     * @return the name of the html file stored in the templates folder
     */
    @GetMapping("users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }
}
