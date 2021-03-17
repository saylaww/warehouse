package uz.pdp.warehouse.controller;

import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceSubTreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    //Create
    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        Result result = outputService.add(outputDto);
        return result;
    }

    //Read List
    @GetMapping
    public List<Output> getOutputs(){
        List<Output> outputs = outputService.getAll();
        return outputs;
    }

    //Read one
    @GetMapping("/{id}")
    public Output getOutput(@PathVariable Integer id){
        Output output = outputService.getOne(id);
        return output;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        Result result = outputService.update(id, outputDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        Result result = outputService.delete(id);
        return result;
    }


}
