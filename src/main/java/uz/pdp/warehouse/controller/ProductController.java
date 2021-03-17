package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //Create
    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return result;
    }

    //Read List
    @GetMapping
    public List<Product> getProducts(){
        List<Product> products = productService.getAll();
        return products;
    }

    //Read One
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id){
        Product product = productService.getOne(id);
        return product;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        Result result = productService.update(id, productDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        Result result = productService.delete(id);
        return result;
    }

}
