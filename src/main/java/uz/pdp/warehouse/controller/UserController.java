package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.payload.UserDto;
import uz.pdp.warehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //Create
    @PostMapping
    public Result addUser(@RequestBody UserDto userDto){
        Result result = userService.add(userDto);
        return  result;
    }

    //Read List
    @GetMapping
    public List<User> getUsers(){
        List<User> users = userService.getAll();
        return users;
    }

    //Read one
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        User user = userService.getOne(id);
        return user;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        Result result = userService.update(id, userDto);
        return result;
    }

    //delete
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        Result result = userService.delete(id);
        return result;
    }

}
