package pl.dopierala.Controllers;

import pl.dopierala.Domain.User;
import pl.dopierala.Domain.UserDTO;
import pl.dopierala.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController("/api")
public class UserRestController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public List<User> getUsersREST() {
        String response = "";
        return userRepo.getUsers();
    }

    @GetMapping("/userById")
    public String getUserByID(@RequestParam int id) {
        String response = "";
        User user = userRepo.getById(id);
        if (Objects.nonNull(user)) {
            return user.toString();
        } else {
            return "User not found.";
        }
    }

    @PutMapping("/saveUser")
    public void saveUserREST(@RequestParam String name,
                             @RequestParam String lastName,
                             @RequestParam int age,
                             @RequestParam boolean sex) {
        User newUser = new User(name, lastName, age, sex);
        userRepo.saveUser(newUser);
    }

    @PutMapping("/saveUserBody")
    public void saveUserRESTbody(@RequestBody User newUser){
        userRepo.saveUser(newUser);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUserREST(@RequestParam int id) {
        User usrDeleted;
        if (Objects.isNull(usrDeleted = userRepo.deleteUserById(id))) {
            return "User not found.";
        } else {
            return "User updated.";
        }
    }

    @GetMapping("/updateUser")
    public String updateUserREST(@RequestParam int id,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam(required = false) Integer age,
                                 @RequestParam(required = false) Boolean sex){
        UserDTO dtoUser = new UserDTO();
        dtoUser.age=age;
        dtoUser.sex=sex;
        dtoUser.name=name;
        dtoUser.lastName=lastName;

        User updatedUser = userRepo.updateUserById(id,dtoUser);
        if(updatedUser == null) {
            return "User not found.";
        } else {
            return "User updated.";
        }
    }
}
