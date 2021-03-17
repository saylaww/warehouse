package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputproduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    //Create
    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        inputProductService.add(inputProductDto);
        return new Result();
    }

    //Read List
    @GetMapping
    public List<InputProduct> getInputProducts(){
        List<InputProduct> all = inputProductService.getAll();
        return all;
    }

    //Read One
    @GetMapping("/{id}")
    public InputProduct getInput(@PathVariable Integer id){
        InputProduct inputProduct = inputProductService.getOne(id);
        return inputProduct;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateInput(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.update(id, inputProductDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        Result result = inputProductService.delete(id);
        return result;
    }

}
