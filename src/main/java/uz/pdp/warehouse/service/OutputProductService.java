package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.OutputProductRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    ProductRepository productRepository;

    public Result add(OutputProductDto outputProductDto) {
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAnmount(outputProductDto.getAnmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()){
            return new Result("Output id not found", false);
        }
        Output output = optionalOutput.get();
        outputProduct.setOutput(output);

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("Product id not found", false);
        }
        Product product = optionalProduct.get();
        outputProduct.setProduct(product);

        outputProductRepository.save(outputProduct);

        return new Result("OutputProduct saved", true);
    }

    public List<OutputProduct> getAll() {
        List<OutputProduct> outputProducts = outputProductRepository.findAll();
        return outputProducts;
    }

    public OutputProduct getOne(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()){
            return new OutputProduct();
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        return outputProduct;
    }

    public Result update(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()){
            return new Result("OutputPRoduct id not found", false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setAnmount(outputProductDto.getAnmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("Product id not found", false);
        }
        Product product = optionalProduct.get();
        outputProduct.setProduct(product);

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()){
            return new Result("Output id not found", false);
        }
        Output output = optionalOutput.get();
        outputProduct.setOutput(output);

        outputProductRepository.save(outputProduct);

        return new Result("OutputProduct updated", true);
    }

    public Result delete(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()){
            return new Result("OutputProduct id not found", false);
        }
        outputProductRepository.deleteById(id);

        return new Result("OutputProduct deleted", true);
    }
}
