package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(Warehouse warehouse) {

        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName){
            return new Result("Warehouse name have in db", false);
        }
        warehouseRepository.save(warehouse);
        return new Result("Warehouse saved", true);
    }

    public List<Warehouse> getAll() {

        List<Warehouse> warehouses = warehouseRepository.findAllByActive(true);
        return warehouses;
    }

    public Warehouse getOne(Integer id) {
        boolean active = warehouseRepository.getActiveById(id);
        if (!active){
            return new Warehouse();
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            return new Warehouse();
        }
        Warehouse warehouse = optionalWarehouse.get();
        return warehouse;
    }

    public Result update(Integer id, Warehouse warehouse) {
        boolean active = warehouseRepository.getActiveById(id);
        if (!active){
            return new Result("This id DeActive", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            return new Result("Warehouse id not found", false);
        }
        Warehouse warehouseDb = optionalWarehouse.get();
        warehouseDb.setName(warehouse.getName());

        warehouseRepository.save(warehouseDb);

        return new Result("Warehouse updated", true);
    }

    public Result delete(Integer id) {
        boolean active = warehouseRepository.getActiveById(id);
        if (!active){
            return new Result("This warehouse id DeActive", false);
        }

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            return new Result("Warehouse id not found", false);
        }
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted", true);
    }

}
