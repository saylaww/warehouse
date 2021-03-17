package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.*;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentRepository;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.MeasurementRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists){
            return new Result("Bunday mahsulot ushbu kategoriada mavjud", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new Result("Bunday kategorya mavjud emas", false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()){
            return new Result("Bunday rasm mavjud emas", false);
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new Result("Bunday ulchov birligi mavjud emas", false);
        }

        Product product = new Product();
        product.setName(productDto.getName());

        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode(productDto.getCode());
        productRepository.save(product);

        return new Result("Mahsulot saqlandi", true);

    }

    public List<Product> getAll() {
        List<Product> productList = productRepository.findAllByActive(true);
        return productList;
    }

    public Product getOne(Integer id) {
        boolean active = productRepository.getActiveById(id);
        if (!active){
            return new Product();
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()){
            return new Product();
        }
        Product product = optionalProduct.get();
        return product;
    }

    public Result update(Integer id, ProductDto productDto) {
        boolean active = productRepository.getActiveById(id);
        if (!active){
            return new Result("THis Product id DeActive", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()){
            return new Result("Product id not found", false);
        }
        Product product = optionalProduct.get();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new Result("Category id not found", false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()){
            return new Result("Photo id not found", false);
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new Result("Measurement id not found", false);
        }

        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);

        return new Result("Product updated", true);
    }

    public Result delete(Integer id) {
        boolean active = productRepository.getActiveById(id);
        if (!active){
            return new Result("This Product id DeActive", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()){
            return new Result("Product id not found", false);
        }
        productRepository.deleteById(id);
        return new Result("Product deleted", true);
    }
}
