package ch.zli.financemanager.controller;

import ch.zli.financemanager.entity.User;
import ch.zli.financemanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method uses the userservice to get all users stored in the database
     * @return a list of all users stored in the database
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * takes a user as a parameter and stores it in the database
     * @param user
     * @return returns the user, which got stored in the database
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * this method deletes a user by its id and throws an exception if there isn't a user with the defined id
     * @param id
     * @throws Exception
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) throws Exception {
        userService.deleteUserById(id);
    }

    /**
     * this method updates a user by its id. if it doesn't exist, it'll throw an exception.
     * @param user
     * @param id
     * @return the updated user
     * @throws Exception
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody User user, @PathVariable long id) throws Exception {
        return userService.updateUser(user, id);
    }

    /**
     * this method is used because plain html can't do method="delete" and if you use th:method="delete", which would work, you can't redirect the user in order to show the changes.
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("delete/{id}")
    public String postDeleteUser(@PathVariable long id) throws Exception {
        deleteUser(id);
        return "redirect:/fe/users";
    }

    /**
     * this method is used because plain html can't do method="update" and if you use th:method="update", which would work, you can't redirect the user in order to show the changes.
     * @param user
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("update/{id}")
    public String postUpdateUser(@ModelAttribute User user, @PathVariable long id) throws Exception {
        updateUser(user, id);
        return "redirect:/fe/users";
    }

    /**
     * this method is used to redirect the user after creating a user.
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("create")
    public String postCreateUser(@ModelAttribute User user) throws Exception {
        createUser(user);
        return "redirect:/fe/users";
    }
}
