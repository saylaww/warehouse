package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.User;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

//    boolean existsByName(String name);

    @Query(value = "select active from category where id=:id", nativeQuery = true)
    boolean getActiveById(@Param("id") Integer id);

    List<Category> findAllByActive(boolean active);

}
