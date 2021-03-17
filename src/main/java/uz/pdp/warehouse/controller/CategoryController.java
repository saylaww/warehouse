package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //Crate
    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    //Get List
    @GetMapping
    public List<Category> getCategories(){
        List<Category> categories = categoryService.getAll();
        return categories;
    }

    //Get one
    @GetMapping("/{id}")
    public Category getOneCategory(@PathVariable Integer id){
        Category category = categoryService.getOne(id);
        return category;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        Result result = categoryService.update(id, categoryDto);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        Result result = categoryService.delete(id);
        return result;

    }

}
