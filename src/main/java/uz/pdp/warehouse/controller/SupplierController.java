package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.payload.SupplierDto;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    //Create
    @PostMapping
    public Result addSupplier(@RequestBody SupplierDto supplierDto){
        Result result = supplierService.add(supplierDto);
        return result;
    }

    //Read
    @GetMapping
    public List<Supplier> getSuppliers(){
        List<Supplier> suppliers = supplierService.getAll();
        return suppliers;
    }

    //Read One
    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable Integer id){
        Supplier supplier = supplierService.getOne(id);
        return supplier;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateSupplier(@PathVariable Integer id, @RequestBody SupplierDto supplierDto){
        Result result = supplierService.update(id, supplierDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        Result result = supplierService.delete(id);
        return result;
    }

}
