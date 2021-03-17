package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.payload.UserDto;
import uz.pdp.warehouse.repository.UserRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(UserDto userDto) {
//        boolean exists = userRepository.existsByPhoneNumberAndCode(userDto.getPhoneNumber(), userDto.getCode());
//        if (!exists){
//            return new Result("Bunday phoneNumber va Code bazada bor!", false);
//        }
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(userDto.getCode());
        user.setPassword(userDto.getPassword());

        List<Integer> warehouses = userDto.getWarehouses();

        Set<Warehouse> list = new HashSet<>();

        for (Integer integer : warehouses) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(integer);
            if (optionalWarehouse.isPresent()){
                list.add(optionalWarehouse.get());
            }
        }
        user.setWarehouses(list);
        userRepository.save(user);

        return new Result("User saved", true);
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAllByActive(true);
        return users;
    }

    public User getOne(Integer id) {
        boolean active = userRepository.getActiveById(id);
        if (!active){
            return new User();
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new User();
        }
        User user = optionalUser.get();
        return user;
    }

    public Result update(Integer id, UserDto userDto) {
        boolean active = userRepository.getActiveById(id);
        if (!active){
            return new Result("This User id DeActive", false);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new Result("User id not found", false);
        }
        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(userDto.getCode());

        List<Integer> warehouses = userDto.getWarehouses();

        Set<Warehouse> list = new HashSet<>();
        for (Integer integer : warehouses) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(integer);
            if (optionalWarehouse.isPresent()){
                list.add(optionalWarehouse.get());
            }
        }
        user.setWarehouses(list);

        userRepository.save(user);

        return new Result("User updated", true);
    }

    public Result delete(Integer id) {
        boolean active = userRepository.getActiveById(id);
        if (!active){
            return new Result("THis User id DeActive", false);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new Result("User id not found", false);
        }
        userRepository.deleteById(id);
        return new Result("User deleted", true);
    }
}
