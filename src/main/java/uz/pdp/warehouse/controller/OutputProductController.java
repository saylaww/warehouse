package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.OutputProductRepository;
import uz.pdp.warehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputproduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    //Create
    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.add(outputProductDto);
        return result;
    }

    //Get List
    @GetMapping
    public List<OutputProduct> getOutputProducts(){
        List<OutputProduct> outputProducts = outputProductService.getAll();
        return outputProducts;
    }

    //Get One
    @GetMapping("/{id}")
    public OutputProduct getOutputProduct(@PathVariable Integer id){
        OutputProduct outputProduct = outputProductService.getOne(id);
        return outputProduct;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateOutputProduct(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.update(id, outputProductDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id){
        Result result = outputProductService.delete(id);
        return result;
    }


}
