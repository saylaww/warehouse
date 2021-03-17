package uz.pdp.warehouse.controller;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    //Create
    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        Result result = inputService.add(inputDto);
        return result;
    }

    //Read List
    @GetMapping
    public List<Input> getInputs(){
        List<Input> inputs = inputService.getAll();
        return inputs;
    }

    //Read One
    @GetMapping("/{id}")
    public Input getInput(@PathVariable Integer id){
        Input input = inputService.getOne(id);
        return input;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        Result result = inputService.update(id, inputDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        Result result = inputService.delete(id);
        return result;
    }

}
