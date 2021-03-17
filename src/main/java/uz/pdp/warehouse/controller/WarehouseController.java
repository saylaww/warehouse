package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    //Create
    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        Result result = warehouseService.add(warehouse);
        return result;
    }

    //Read List
    @GetMapping
    public List<Warehouse> getWarehouses(){
        List<Warehouse> warehouses = warehouseService.getAll();
        return warehouses;
    }

    //Read one
    @GetMapping("/{id}")
    public Warehouse getWarehouse(@PathVariable Integer id){
        Warehouse warehouse = warehouseService.getOne(id);
        return warehouse;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        Result result = warehouseService.update(id, warehouse);
        return result;
    }

    //Delete
    @DeleteMapping("{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        Result result = warehouseService.delete(id);
        return result;
    }

}
