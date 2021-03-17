package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.InputProductRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    InputRepository inputRepository;

    public Result add(InputProductDto inputProductDto) {
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAnmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()){
            return new Result("Input id not found", false);
        }
        Input input = optionalInput.get();
        inputProduct.setInput(input);

        inputProductRepository.save(inputProduct);

        return new Result("Input saved", true);
    }

    public List<InputProduct> getAll() {
        List<InputProduct> all = inputProductRepository.findAll();
        return all;
    }

    public InputProduct getOne(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()){
            return new InputProduct();
        }
        InputProduct inputProduct = optionalInputProduct.get();
        return inputProduct;
    }

    public Result update(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> inputProductOptional = inputProductRepository.findById(id);
        if (!inputProductOptional.isPresent()){
            return new Result("In",false);
        }
        InputProduct inputProduct = inputProductOptional.get();
        inputProduct.setAmount(inputProductDto.getAnmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()){
            return new Result("Input id not found", false);
        }
        Input input = optionalInput.get();
        inputProduct.setInput(input);

        inputProductRepository.save(inputProduct);

        return new Result("InputProduct updated", true);
    }

    public Result delete(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()){
            return new Result("InputProduct id not found", false);
        }
        inputProductRepository.deleteById(id);
        return new Result("InputProduct deleted", true);
    }
}
