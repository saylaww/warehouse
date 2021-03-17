package uz.pdp.warehouse.service;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.SupplierRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(InputDto inputDto) {
        boolean exists = inputRepository.existsByCodeAndFactureNumber(inputDto.getCode(), inputDto.getFactureNumber());
        if (exists){
            return new Result("Input code and FacturNumber have in db", false);
        }
        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setCode(inputDto.getCode());
        input.setFactureNumber(inputDto.getFactureNumber());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("Warehouse id not found", false);
        }
        Warehouse warehouse = optionalWarehouse.get();
        input.setWarehouse(warehouse);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()){
            return new Result("Supplier id not found", false);
        }
        Supplier supplier = optionalSupplier.get();
        input.setSupplier(supplier);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency id not found", false);
        }
        Currency currency = optionalCurrency.get();
        input.setCurrency(currency);

        inputRepository.save(input);

        return new Result("Input Saved", true);
    }

    public List<Input> getAll() {
        List<Input> inputs = inputRepository.findAll();
        return inputs;
    }

    public Input getOne(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()){
            return new Input();
        }
        Input input = optionalInput.get();
        return input;
    }

    public Result update(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()){
            return new Result("Input id not found", false);
        }
        Input input = optionalInput.get();
        input.setDate(inputDto.getDate());
        input.setCode(inputDto.getCode());
        input.setFactureNumber(inputDto.getFactureNumber());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("Warehouse id not found", false);
        }
        Warehouse warehouse = optionalWarehouse.get();
        input.setWarehouse(warehouse);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()){
            return new Result("Supplier id not found", false);
        }
        Supplier supplier = optionalSupplier.get();
        input.setSupplier(supplier);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency id not found", false);
        }
        Currency currency = optionalCurrency.get();
        input.setCurrency(currency);

        inputRepository.save(input);

        return new Result("Input updated", true);
    }

    public Result delete(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()){
            return new Result("Input id not found", false);
        }
        inputRepository.deleteById(id);
        return new Result("Input deleted", true);
    }
}
