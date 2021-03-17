package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Result addCategory(CategoryDto categoryDto){

        Category category = new Category();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!categoryOptional.isPresent()){
                return new Result("Bunday ota category mavjud emas", false);
            }
            category.setParentCategory(categoryOptional.get());
        }
        categoryRepository.save(category);
        return  new Result("Muvaffaqiyatli saqlandi", true);
    }

    public List<Category> getAll() {
        List<Category> list = categoryRepository.findAllByActive(true);
        return list;
    }

    public Category getOne(Integer id) {
        boolean active = categoryRepository.getActiveById(id);
        if (!active){
            return new Category();
        }

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Category();
        }
        Category category = optionalCategory.get();

        return category;
    }

    public Result update(Integer id, CategoryDto categoryDto) {
        boolean active = categoryRepository.getActiveById(id);
        if (!active){
            return new Result("This Category id DeActive", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Result("Category id bazada topilmadi", false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent()){
                return new Result("Bunday ota category mavjud emas", false);
            }
            category.setParentCategory(optionalCategory.get());
        }

        categoryRepository.save(category);
        return new Result("Category Updated", true);
    }

    public Result delete(Integer id) {
        boolean active = categoryRepository.getActiveById(id);
        if (!active){
            return new Result("This category id DeActive", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Result("Bunday category id bazada topilmadi", false);
        }
        categoryRepository.deleteById(id);
        return new Result("Category o'chirildi", true);
    }
}
