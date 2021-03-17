package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.payload.SupplierDto;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result add(SupplierDto supplierDto) {
        boolean exists = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (exists){
            return new Result("Supplier phone number have in db", false);
        }
        Supplier supplier = new Supplier();
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setName(supplierDto.getName());

        supplierRepository.save(supplier);
        return new Result("Supplier saved", true);
    }

    public List<Supplier> getAll() {
        List<Supplier> suppliers = supplierRepository.findAllByActive(true);
        return suppliers;
    }

    public Supplier getOne(Integer id) {
        boolean active = supplierRepository.getActiveById(id);
        if (!active){
            return new Supplier();
        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            return new Supplier();
        }
        Supplier supplier = optionalSupplier.get();
        return supplier;
    }

    public Result update(Integer id, SupplierDto supplierDto) {
        boolean active = supplierRepository.getActiveById(id);
        if (!active){
            return new Result("This Supplier id DeActive", false);
        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            return new Result("Supplier id not found", false);
        }
        Supplier supplier = optionalSupplier.get();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());

        supplierRepository.save(supplier);

        return new Result("Supplier updated", true);
    }

    public Result delete(Integer id) {
        boolean active = supplierRepository.getActiveById(id);
        if (!active){
            return new Result("This Supplier id DeActive", false);
        }

        boolean existsById = supplierRepository.existsById(id);
        if (!existsById){
            return new Result("Supplier id not found", false);
        }
        supplierRepository.deleteById(id);
        return new Result("Supplier deleted", true);
    }
}
