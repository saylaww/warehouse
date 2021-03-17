package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ClientRepository clientRepository;

    public Result add(OutputDto outputDto) {
        boolean existsByCode = outputRepository.existsByCodeAndFactureNumber(outputDto.getCode(), outputDto.getFactureNumber());
        if (existsByCode){
            return new Result("Output code and FacturNumber have in db", false);
        }
        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(outputDto.getCode());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return  new Result("Warehouse id not found", false);
        }
        Warehouse warehouse = optionalWarehouse.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency id not found", false);
        }
        Currency currency = optionalCurrency.get();

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()){
            return new Result("Client id not found", false);
        }
        Client client = optionalClient.get();

        output.setWarehouse(warehouse);
        output.setCurrency(currency);
        output.setClient(client);

        outputRepository.save(output);

        return new Result("Output saved", true);
    }

    public List<Output> getAll() {
        List<Output> outputs = outputRepository.findAll();
        return outputs;
    }

    public Output getOne(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()){
            return new Output();
        }
        Output output = optionalOutput.get();
        return output;
    }

    public Result update(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()){
            return new Result("Output id not found", false);
        }
        Output output = optionalOutput.get();
        output.setDate(outputDto.getDate());
        output.setCode(outputDto.getCode());
        output.setFactureNumber(outputDto.getFactureNumber());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("Warehouse id not found", false);
        }
        Warehouse warehouse = optionalWarehouse.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("Currency id not found", false);
        }
        Currency currency = optionalCurrency.get();

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()){
            return new Result("Client id not found", false);
        }
        Client client = optionalClient.get();

        output.setWarehouse(warehouse);
        output.setCurrency(currency);
        output.setClient(client);

        outputRepository.save(output);

        return new Result("Output updated", true);
    }

    public Result delete(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()){
            return new Result("Output id not found", false);
        }
        outputRepository.deleteById(id);
        return new Result("Output deleted", true);
    }
}
